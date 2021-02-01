package com.sammidev

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ResourceLoaderAware
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import java.util.*

@SpringBootApplication
@PropertySources(
        PropertySource("classpath:/configuration/sample.properties"),
        PropertySource("classpath:/configuration/sample2.properties")
)
class Concept8

fun main() {
    val context = SpringApplication.run(Concept8::class.java)
    println(context.getBean(Author::class.java))
}

@Component
class FileBean : ResourceLoaderAware {
    lateinit var rl: ResourceLoader

    override fun setResourceLoader(p0: ResourceLoader) {
        this.rl = p0
    }

    fun getinfo() {
        val resource = rl.getResource("classpath:/resources/hi.txt").inputStream
        val scan = Scanner(resource)
        while (scan.hasNextLine()){
            val line = scan.nextLine()
            println(line)
        }
        scan.close()
    }
}

@Component
data class Author(
        @param:Value("\${author.name}") val name:String,
        @param:Value("\${author.since}") val since:String,
)

//@Component
//data class Author2(
//        @param:Value("\${author.name1}") val name:String,
//        @param:Value("\${author.since2}") val since:String,
//)