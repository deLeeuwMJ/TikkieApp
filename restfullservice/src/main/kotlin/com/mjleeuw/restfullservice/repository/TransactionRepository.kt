package com.mjleeuw.restfullservice.repository

import org.springframework.web.bind.annotation.*
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.dao.IncorrectResultSizeDataAccessException

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.model.Payment
import com.mjleeuw.restfullservice.model.Request
import com.mjleeuw.restfullservice.services.TransactionService

interface TransactionRepository : CrudRepository<Transaction, String>{

    // Get requests

    @Query("SELECT * FROM transactions")
    fun findTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions t WHERE t.transaction_code = :gcode")
    fun findTransactionByCode(@Param("gcode") givenCode: String): Transaction

    @Query("SELECT * FROM payments")
    fun findPayments(): List<Payment>

    @Query("SELECT * FROM payments p WHERE p.transaction_id = :id")
    fun findPaymentsById(@Param("id") id: Int): List<Payment>

    @Query("SELECT * FROM requests")
    fun findRequests(): List<Request>

    @Query("SELECT * FROM requests r WHERE r.transaction_id = :id")
    fun findRequestsById(@Param("id") id: Int): List<Request>
}