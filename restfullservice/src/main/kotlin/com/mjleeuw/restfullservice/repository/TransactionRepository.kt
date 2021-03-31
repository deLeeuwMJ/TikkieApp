package com.mjleeuw.restfullservice.repository

import org.springframework.web.bind.annotation.*
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.dao.IncorrectResultSizeDataAccessException

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.services.TransactionService

interface TransactionRepository : CrudRepository<Transaction, String>{

    @Query("SELECT * FROM transactions")
    fun findTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions t WHERE t.code = :gcode")
    fun findTransactionByCode(@Param("gcode") givenCode: String): Transaction

    @Query("DELETE FROM transactions")
    fun resetTransactions(): Void
}