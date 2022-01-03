package com.pboucher.mailsender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MailSenderApplication

fun main(args: Array<String>) {
	runApplication<MailSenderApplication>(*args)
}
