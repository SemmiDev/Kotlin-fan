package com.sammidev

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Concept {

	@Bean(name = ["student1"])
	fun createStudentBean1(): Student = Student(1,"sammidev 1")

	@Bean(name = ["student2"])
	fun createStudentBean2(): Student = Student(2,"sammidev 2")

	@Bean
	fun createSampleBean1(@Qualifier("student1") student: Student) : SampleBean = SampleBean(student)

	@Bean
	fun createSampleBean2(@Qualifier("student2") student: Student, sampleBean: SampleBean) : SampleBean2 = SampleBean2(student, sampleBean)

	// error
//	@Bean
//	fun aBean(e: E) : A = A(e)
//
//	@Bean
//	fun bBean(a: A) : B = B(a)
//
//	@Bean
//	fun cBean(b: B) : C = C(b)
//
//	@Bean
//	fun dBean(c: C) : D = D(c)
//
//	@Bean
//	fun eBean(d: D) : E = E(d)
}

fun main(args: Array<String>) {

	val context = SpringApplication.run(Concept::class.java)
	val sampleBean = context.getBean(SampleBean::class.java)

	println("=====================")
	println(sampleBean.student)
	println("=====================")

	val sampleBean2 = context.getBean(SampleBean2::class.java)
	println("=====================")
	println(sampleBean2.student) // sammidev2
	println(sampleBean2.sampleBean) // sammidev1
	println("=====================")

//	val a = context.getBean(A::class.java)
}

/**
 * Objek yg di create disebut Bean
 * Bean itu disimpan di Container
 * Container direpresentasikan oleh applicationContext
 */

data class Student(val id:Int, val name:String)

data class SampleBean(
		val student: Student
)

data class SampleBean2(
		val student: Student,
		val sampleBean: SampleBean)



// error
//data class A (val e: E)
//data class B (val a: A)
//data class C (val b: B)
//data class D (val c: C)
//data class E (val d: D)