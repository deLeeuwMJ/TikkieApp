package com.mjleeuw.restfullservice.model

import org.springframework.web.bind.annotation.*

import org.springframework.data.annotation.*
import org.springframework.data.relational.core.mapping.Table

@Table("TRANSACTIONS")
data class Transaction(
    @Id val transaction_id: Int,
    val transaction_code: String,
    val transaction_description: String
)

@Table("PAYMENTS")
data class Payment(
    @Id val payment_id: Int,
    val transaction_id: Int,
    val payment_sender_name: String,
    val payment_creation_date: String,
    val payment_description: String,
    val payment_amount: Double
)

@Table("REQUESTS")
data class Request(
    @Id val request_id: Int,
    val transaction_id: Int,
    val request_sender_name: String,
    val request_receiver_name: String,
    val request_creation_date: String,
    val request_description: String,
    val request_amount: Double
)