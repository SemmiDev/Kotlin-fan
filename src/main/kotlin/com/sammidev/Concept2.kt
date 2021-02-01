package com.sammidev

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class Concept2

fun main(args: Array<String>) {
    val context = SpringApplication.run(Concept2::class.java)

    val sampleComponent = context.getBean(SampleComponent::class.java)
    val sampleComponent2 = context.getBean(SampleComponent2::class.java)

    println(sampleComponent.value)
    println(sampleComponent2.sampleComponent)
    println(sampleComponent2.toUpper())
}

// @Component = otomatis dia akan membuatkan bean untuk kita

@Component
data class SampleComponent(val value: String = "sammidev")

@Component
data class SampleComponent2(val sampleComponent: SampleComponent) {
    fun toUpper() = sampleComponent.value.toUpperCase()
}