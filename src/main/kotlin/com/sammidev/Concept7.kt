package com.sammidev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
class Concept7 {

    @Bean
    @Profile(value = ["development"])
    fun dbConfigDev(): DatabaseConfig  = DatabaseConfig("POSTGRES FOR DEVELOPEMENT")

    @Bean
    @Profile(value = ["production"])
    fun dbConfigProd(): DatabaseConfig  = DatabaseConfig("POSTGRES FOR PRODUCTION")
}

fun main(args: Array<String>) {
    System.setProperty("spring.profiles.active", "development")
    val context = SpringApplication.run(Concept7::class.java)
    println(context.getBean(DatabaseConfig::class.java).value)

    // di runtime, run pake = -Dspring.profiles.active="production"
}

data class DatabaseConfig(val value: String)