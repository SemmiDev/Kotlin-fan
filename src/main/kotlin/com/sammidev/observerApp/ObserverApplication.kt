package com.sammidev.observerApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean

class ObserverApplication {
    companion object {

        data class Course(
                val Id: String,
                val Name: String,
                val sks: Int,
                val semester: Int,
        )
        data class Student(
                val id: String,
                val name: String,
                val email: String,
                val phone: String,
        )

        class StudentEvent(source: Student) : ApplicationEvent(source)
        class CourseEvent(source: Course) : ApplicationEvent(source)

        class MessageBrokerObserver : ApplicationListener<StudentEvent> {
            override fun onApplicationEvent(event: StudentEvent) {
                println("SENT TO MESSAGE BROKER")
            }
        }

        class LogObserver : ApplicationListener<CourseEvent> {
            override fun onApplicationEvent(event: CourseEvent) {
                println("SENT TO LOG SERVER")
            }
        }

        class StudentRepository : ApplicationEventPublisherAware {
            private lateinit var publisher: ApplicationEventPublisher
            override fun setApplicationEventPublisher(publisher: ApplicationEventPublisher){
                this.publisher = publisher
            }

            fun save(student: Student, course: Course) {
                println("DONE SAVE TO DB")
                publisher.publishEvent(StudentEvent(student))
                publisher.publishEvent(CourseEvent(course))
            }
        }

        class RedisObserver : ApplicationListener<ApplicationEvent> {
            override fun onApplicationEvent(event: ApplicationEvent) {
                if (event is StudentEvent) {
                    println("SENT STUDENT TO REDIS SERVER")
                } else if (event is CourseEvent) {
                    println("SENT COURSE TO REDIS SERVER")
                }
            }
        }

        @SpringBootApplication
        class Application {

            @Bean
            fun studentRepository(): StudentRepository {
                return StudentRepository()
            }

            @Bean
            fun messageBrokerObserver(): MessageBrokerObserver {
                return MessageBrokerObserver()
            }

            @Bean
            fun redisObserver(): RedisObserver {
                return RedisObserver()
            }

            @Bean
            fun logObserver(): LogObserver {
                return LogObserver()
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Application::class.java)
            val productRepository = context.getBean(StudentRepository::class.java)
            val course = Course("1","sammidev",4,1)
            productRepository.save(Student("1", "Sammi Aldhi Yanto", "sammidev4@gmail.com", "08323873242"),course)
        }
    }
}