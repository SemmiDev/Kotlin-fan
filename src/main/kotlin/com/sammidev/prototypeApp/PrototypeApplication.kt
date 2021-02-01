package com.sammidev.prototypeApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope

class PrototypeApplication {
    companion object {
        enum class PersonCategory {
            RICH,POOR;
        }
        data class Person(val rich: PersonCategory, val name: String)

        @SpringBootApplication
        class Config {

            @Bean("richPerson")
            @Scope("prototype")
            fun richPerson(): Person {
                return Person(PersonCategory.RICH, "sammidev")
            }

            @Bean("poorPerson")
            @Scope("prototype")
            fun poorPerson(): Person {
                return Person(PersonCategory.POOR, "xxx")
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Config::class.java)

            val person1 = context.getBean("richPerson", Person::class.java)
            val person2 = context.getBean("richPerson", Person::class.java)

            val person3 = context.getBean("poorPerson", Person::class.java)
            val person4 = context.getBean("poorPerson", Person::class.java)

            println(person1 == person2)
            println(person3 == person4)
            println(person1 == person3)

            println(person1)
            println(person2)
            println(person3)
            println(person4)
        }
    }
}