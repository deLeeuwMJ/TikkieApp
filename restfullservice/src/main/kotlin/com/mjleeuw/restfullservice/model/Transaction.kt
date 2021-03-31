package com.mjleeuw.restfullservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("transactions")
data class Transaction(
    val id: String?,
    val code: String,
    val sname: String,
    val cdate: String,
    val amount: Double
)