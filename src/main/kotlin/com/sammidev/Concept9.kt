package com.sammidev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.validation.DataBinder
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@SpringBootApplication
class Concept9

fun main() {
    SpringApplication.run(Concept9::class.java)

    val teacher = Teacher("","123")
    val binder = DataBinder(teacher)
    binder.addValidators(TeacherValidator())
    binder.validate()
    val error = binder.bindingResult
    if (error.hasErrors()) {
        error.allErrors.forEach {
            println(it.code)
        }
    }else {
        println("no error")
    }
}

class Teacher(val name:String, val phone: String)

class TeacherValidator : Validator {
    override fun validate(target: Any, errors: Errors) {

        val teacher: Teacher = target as Teacher
        val tes = teacher.name.trim().isEmpty()

        val tesPhoneName = teacher.phone.trim().isEmpty()
        val tesPhone = teacher.phone.length != 10

        if (teacher.name == null || tes) {
            errors.rejectValue("name","tak bole kosong")
        }
        if (teacher.phone == null || tesPhoneName || tesPhone) {
            errors.rejectValue("phone", "tak bole kosong dan max values 10 biji xixixix")
        }
    }

    override fun supports(classygdisuppport: Class<*>): Boolean {
        // class teacher dan turunanya akan di validasi
        return Teacher::class.java.isAssignableFrom(classygdisuppport)
    }
}