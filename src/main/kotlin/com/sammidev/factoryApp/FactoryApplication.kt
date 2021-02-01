package com.sammidev.factoryApp

import org.springframework.beans.factory.FactoryBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*

class FactoryApplication {
    companion object {
        data class PostgresSQLDatabase(
                val host: String,
                val port: Int,
                val username: String,
                val password: String,
        )
        data class MySQLDatabase(
                val host: String,
                val port: Int,
                val username: String,
                val password: String,
        )
        data class MongoDBDatabase(
                val host: String,
                val port: Int,
                val username: String,
                val password: String,
        )
        data class Database(
                val postgresDatabase: PostgresSQLDatabase,
                val mySQLDatabase: MySQLDatabase,
                val mongoDBDatabase: MongoDBDatabase
        )
        data class Student(
                val name:String = "sammidev", val nim: UUID = UUID.randomUUID()
        )

        class DatabaseFactory : FactoryBean<Database> {
            override fun getObject(): Database? {
                return Database(
                        PostgresSQLDatabase("localhost",27017,"sammidev","sammidev"),
                        MySQLDatabase("localhost",3306,"sammidev","sammidev"),
                        MongoDBDatabase("localhost",9000,"sammidev","sammidev")
                )
            }

            override fun getObjectType(): Class<*>? {
                return Database::class.java
            }
        }

        class StudentFactory : FactoryBean<Student> {
            override fun getObject(): Student? {
                return Student()
            }

            override fun getObjectType(): Class<*>? {
                return Student::class.java
            }
        }

        @SpringBootApplication
        class Config {
            @Bean
            fun database(): FactoryBean<Database> {
                return DatabaseFactory()
            }
            @Bean
            fun student(): FactoryBean<Student> {
                return StudentFactory()
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Config::class.java)
            val database = context.getBean(Database::class.java)
            val student = context.getBean(Student::class.java)

            println(database.mySQLDatabase)
            println(database.postgresDatabase)
            println(student.name)
            println(student.nim)
        }
    }
}