package com.sammidev.prototypeApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@SpringBootApplication
class Concept5

fun main(args: Array<String>) {
    val context = SpringApplication.run(Concept5::class.java)
    context.getBean(Siswa::class.java).doSomething()
}

@Component
data class Siswa(val value: String = "sam") : ApplicationContextAware {

    // applicationAware berguna untuk mengirim context ke bean yg mengimplementkannya
    lateinit var appContext : ApplicationContext
    override fun setApplicationContext(appContext: ApplicationContext) {
        this.appContext = appContext
    }

    fun doSomething() {
        if (this.appContext != null) {
            println("BERISI")
        }else {
            println("TIDAK BERISI")
        }
    }
}