package com.mjleeuw.restfullservice.repository

import org.springframework.web.bind.annotation.*
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.dao.IncorrectResultSizeDataAccessException

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.model.Payment
import com.mjleeuw.restfullservice.services.TransactionService

interface PaymentRepository : CrudRepository<Payment, String>{

    // Get requests

    @Query("SELECT * FROM payments")
    fun findPayments(): List<Payment>

    @Query("SELECT * FROM payments p WHERE p.transaction_id = :id")
    fun findPaymentsById(@Param("id") id: Int): List<Payment>

    @Modifying
    @Query("DELETE FROM payments p WHERE p.transaction_id = :id")
    fun resetPayments(@Param("id") id: Int): Int
}