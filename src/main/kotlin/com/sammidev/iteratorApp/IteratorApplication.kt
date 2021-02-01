package com.sammidev.iteratorApp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal

class IteratorApplication {
    enum class PaymentType {
        BNI,BRI,BCA;
    }
    companion object {
        data class Payment(val paymentType: PaymentType, val amount:BigDecimal)
        interface PaymentService {
            fun isSupport(paymentType: PaymentType) : Boolean
            fun pay(payment: Payment)
        }

        class BNIPaymentService() : PaymentService {
            override fun isSupport(paymentType: PaymentType) : Boolean = paymentType == PaymentType.BNI
            override fun pay(payment: Payment) {
                println("pay BNI")
            }
        }
        class BRIPaymentService() : PaymentService {
            override fun isSupport(paymentType: PaymentType) : Boolean = paymentType == PaymentType.BRI
            override fun pay(payment: Payment) {
                println("pay BRI")
            }
        }
        class BCAPaymentService() : PaymentService {
            override fun isSupport(paymentType: PaymentType) : Boolean = paymentType == PaymentType.BCA
            override fun pay(payment: Payment) {
                println("pay BCA")
            }
        }

        @SpringBootApplication
        class Application {
            @Bean
            fun bniPaymentService() : BNIPaymentService {
                return BNIPaymentService()
            }
            @Bean
            fun briPaymentService() : BRIPaymentService {
                return BRIPaymentService()
            }
            @Bean
            fun bcaPaymentService() : BCAPaymentService {
                return BCAPaymentService()
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Application::class.java)
            val payment = Payment(PaymentType.BRI, BigDecimal(2000000))
            context.getBeansOfType(PaymentService::class.java).values
                    .filter { service -> service.isSupport(payment.paymentType) }[0]
                    .pay(payment)
        }
    }
}