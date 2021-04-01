package com.mjleeuw.restfullservice.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.model.Payment
import com.mjleeuw.restfullservice.model.Request
import com.mjleeuw.restfullservice.repository.TransactionRepository

@Service
class TransactionService(val db: TransactionRepository) {

    // Get requests

    fun findTransactions(): List<Transaction> = db.findTransactions()

    fun findTransactionByCode(code: String): Transaction {
        println("Given code: " + code)
        return db.findTransactionByCode(code)
    }

    fun findPayments(): List<Payment> = db.findPayments()

    fun findPaymentsById(code: String): List<Payment> {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_code)
        return db.findPaymentsById(transaction.transaction_id)
    }

    fun findRequests(): List<Request> = db.findRequests()

    fun findRequestsById(code: String): List<Request> {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_id)
        return db.findRequestsById(transaction.transaction_id)
    }

    // Post requests

    fun post(transaction: Transaction) {
        db.save(transaction)
    }
}