package com.sammidev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@SpringBootApplication
class Concept4

fun main(args: Array<String>) {
    val context = SpringApplication.run(Concept4::class.java)
    context.getBean(DatabaseService::class.java)
}

@Component
class DatabaseService {

    @PostConstruct
    fun openConnection(){
        println("OPEN CONNECTION")
    }

    @PreDestroy
    fun closeConnection(){
        println("CLOSE CONNECTION")
    }
}