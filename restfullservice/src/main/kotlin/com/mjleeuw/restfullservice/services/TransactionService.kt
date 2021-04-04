package com.mjleeuw.restfullservice.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

import com.mjleeuw.restfullservice.model.*
import com.mjleeuw.restfullservice.repository.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.math.BigDecimal 
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.sign

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

    fun calculateTotalRequestsById(code: String): List<Request> {
        println("Given code: " + code)
        var transaction: Transaction = findTransactionByCode(code)
        println("Given id: " + transaction.transaction_code)
        var payments: List<Payment> = paymentRepo.findPaymentsById(transaction.transaction_id)
        return calculateTotals(payments)
    }

    fun calculateTotals(payments: List<Payment>): List<Request> {
        var requestList: ArrayList<Request> = arrayListOf<Request>()
        var amountSpent: Double = 0.0
        var amountPeople: Int = payments.size

        payments.forEach {
            amountSpent += it.payment_amount
        }

        println("Total spent: " + amountSpent + " with " + amountPeople + " people.")

        var getCalculatedSharingExpense: Double = roundOffDecimal(amountSpent/amountPeople)

        println("Each people ownes: " + getCalculatedSharingExpense + " .")

        var credList: ArrayList<Request> = arrayListOf<Request>()
        var debtList: ArrayList<Request> = arrayListOf<Request>()

        payments.forEach {
            var amountOwnedToPot: Double = roundOffDecimal(getCalculatedSharingExpense - it.payment_amount)
            println(it.payment_sender_name + " ownes " + amountOwnedToPot + " .")
            // Kotlin sign checks if value is negative if -1.0 is answer
            if (sign(amountOwnedToPot) == -1.0){
                debtList.add(
                    Request(
                        -1,
                        it.transaction_id,
                        it.payment_sender_name,
                        "Receiver name",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                        "Geen beschrijving opgegeven",
                        amountOwnedToPot
                    )
                )
            } else {
                credList.add(
                    Request(
                        -1,
                        it.transaction_id,
                        it.payment_sender_name,
                        "Receiver name",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                        "Geen beschrijving opgegeven",
                        amountOwnedToPot
                    )
                )
            }
        }

        credList.forEach{
            var totalAmountAvailable: Double = it.request_amount

            for (i in debtList.indices){
                if (totalAmountAvailable > debtList[i].request_amount){
                    requestList.add(
                        Request(
                            -1,
                            debtList[i].transaction_id,
                            it.request_sender_name,
                            debtList[i].request_sender_name,
                            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                            "Geen beschrijving opgegeven",
                            it.request_amount
                        )
                    )
                    totalAmountAvailable = totalAmountAvailable - debtList[i].request_amount
                }
            }
        }

        return requestList;
    }

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
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
        println("Given code: " + code.substring(0, code.length - 1))
        var transaction: Transaction = findTransactionByCode(code.substring(0, code.length - 1))
        println("Given id: " + transaction.transaction_id)
        paymentRepo.resetPayments(transaction.transaction_id)
    }
}