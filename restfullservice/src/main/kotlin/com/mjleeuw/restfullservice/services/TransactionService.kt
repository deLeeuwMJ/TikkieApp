package com.mjleeuw.restfullservice.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

import com.mjleeuw.restfullservice.model.*
import com.mjleeuw.restfullservice.repository.*

@Service
class TransactionService(val transactionRepo: TransactionRepository, val paymentRepo: PaymentRepository, val requestRepo: RequestRepository) {

    // Get requests

    fun findTransactions(): List<Transaction> = transactionRepo.findTransactions()

    fun findTransactionByCode(code: String): Transaction {
        println("Given code: " + code)
        return transactionRepo.findTransactionByCode(code)
    }

    fun findPayments(): List<Payment> = paymentRepo.findPayments()

    fun findPaymentsById(code: String): List<Payment> {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_code)
        return paymentRepo.findPaymentsById(transaction.transaction_id)
    }

    fun findRequests(): List<Request> = requestRepo.findRequests()

    fun findRequestsById(code: String): List<Request> {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_id)
        return requestRepo.findRequestsById(transaction.transaction_id)
    }

    // Post requests

    fun postTransaction(transaction: Transaction) {
        transactionRepo.save(transaction)
    }

    fun postPayment(payment: Payment) {
        paymentRepo.save(payment)
    }

    fun postRequest(request: Request) {
        requestRepo.save(request)
    }

    fun postReset(code: String) {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_id)
        paymentRepo.resetPayments(transaction.transaction_id)
    }
}