package com.sammidev

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class Concept6

fun main(args: Array<String>) {
    val context = SpringApplication.run(Concept6::class.java)

}

// plugin yg dieksetends ke spring tsb
@Component
class Logplugin : InstantiationAwareBeanPostProcessorAdapter() {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        // setiap bean setelah selesai dibuat, maka akan memanggil ini
        println("TELAH DIBUAT BEAN DENGAN NAMA $beanName dengan type bean ${bean::class.java.name}")
        return bean
    }
}