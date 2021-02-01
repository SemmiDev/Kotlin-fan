package com.sammidev.facadeApp

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

class FacadeApplication {
    companion object {
        data class Course(val id:String, val name: String, val sks:Int, val kelas: String, val semester: Int)
        data class Lecture(val id:String,val name:String,val courses:List<Course>)

        interface LectureService {
            fun save(lecture: Lecture)
            fun remove(lecture: Lecture)
            fun update(lecture: Lecture)
        }

        interface CourseService {
            fun save(course: Course)
            fun remove(course: Course)
            fun update(course: Course)
        }

        class LectureController(private val lectureService: LectureService) {
            fun saveLecture(lecture: Lecture) {
                println("LECTURE CONTROLLER")
                lectureService.save(lecture)
            }
        }

        class CourseController(private val lectureService: LectureService) {
            fun saveLecture(lecture: Lecture) {
                println("COURSE CONTROLLER")
                lectureService.save(lecture)
            }
        }

        class LectureServiceImplPostgreSQL : LectureService {
            override fun save(lecture: Lecture) {
                println("save lecture in postgres")
            }

            override fun remove(lecture: Lecture) {
                println("remove lecture from postgres")
            }

            override fun update(lecture: Lecture) {
                println("update lecture from postgres")
            }
        }

        class LectureServiceImplMySQL : LectureService {
            override fun save(lecture: Lecture) {
                println("save lecture in mysql")
            }

            override fun remove(lecture: Lecture) {
                println("remove lecture from mysql")
            }

            override fun update(lecture: Lecture) {
                println("update lecture from mysql")
            }
        }

        class CourseServiceImplMongoDB : CourseService {
            override fun save(course: Course) {
                println("save course in postgres")
            }

            override fun remove(course: Course) {
                println("remove course from postgres")
            }

            override fun update(course: Course) {
                println("update course from postgres")
            }
        }

        class CourseServiceImplCassandra : CourseService {
            override fun save(course: Course) {
                println("save course in cassandra")
            }

            override fun remove(course: Course) {
                println("remove course from cassandra")
            }

            override fun update(course: Course) {
                println("update course from cassandra")
            }
        }

        @SpringBootApplication
        class Application {

            fun lectureServiceMySQL(): LectureServiceImplMySQL = LectureServiceImplMySQL()

            @Bean
            fun lectureServicePostgreSQL(): LectureServiceImplPostgreSQL = LectureServiceImplPostgreSQL()

            fun lectureController(lectureService: LectureService): LectureController {
                return LectureController(lectureService)
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Application::class.java)
            val controller = context.getBean(LectureController::class.java)

            controller.saveLecture(Lecture("sam","sam", listOf(Course("","",5,"",4))))
        }
    }
}