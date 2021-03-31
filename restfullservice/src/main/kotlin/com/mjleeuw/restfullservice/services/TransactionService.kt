package com.mjleeuw.restfullservice.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.repository.TransactionRepository

@Service
class TransactionService(val db: TransactionRepository) {

    fun findTransactions(): List<Transaction> = db.findTransactions()

    fun findTransactionByCode(code: String): Transaction {
        println("Given code: " + code)
        return db.findTransactionByCode(code)
    }

    fun resetAllTransactions(): Void = db.resetTransactions()

    fun post(transaction: Transaction) {
        db.save(transaction)
    }
}