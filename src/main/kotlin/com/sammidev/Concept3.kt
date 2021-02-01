package com.sammidev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope

@SpringBootApplication
class Concept3 {

    // defaulnya singleton, diubah prototype agar ketika manggil objek dan modify properties/ll, tidak akan terganggu ke pemanggil yg lain
    @Bean(name = ["m1"])
    @Scope("prototype")
    fun mahasiswa1() = Mahasiswa("sammi 1")

    @Bean(name = ["m2"])
    fun mahasiswa2() = Mahasiswa("sammi 2")

}

fun main(args: Array<String>) {
    val context = SpringApplication.run(Concept3::class.java)

    val mahasiswa1 = context.getBean("m1", Mahasiswa::class.java)
    val mahasiswa2 = context.getBean("m1", Mahasiswa::class.java)
    val mahasiswa3 = context.getBean("m1", Mahasiswa::class.java)

    println(mahasiswa1.name)
    println(mahasiswa2.name)
    println(mahasiswa3.name)

    mahasiswa1.name = "MODIFY"

    println(mahasiswa1.name)
    println(mahasiswa2.name)
    println(mahasiswa3.name)
}

data class Mahasiswa(var name: String)