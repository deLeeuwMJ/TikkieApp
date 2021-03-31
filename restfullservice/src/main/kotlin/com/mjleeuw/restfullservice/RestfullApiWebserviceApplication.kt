package com.mjleeuw.restfullservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id

@SpringBootApplication
class RestfullApiWebserviceApplication

fun main(args: Array<String>) {
	runApplication<RestfullApiWebserviceApplication>(*args)
}