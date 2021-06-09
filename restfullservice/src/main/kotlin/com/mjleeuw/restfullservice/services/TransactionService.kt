package com.mjleeuw.restfullservice.services

import com.mjleeuw.restfullservice.model.Payment
import com.mjleeuw.restfullservice.model.Request
import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.repository.PaymentRepository
import com.mjleeuw.restfullservice.repository.RequestRepository
import com.mjleeuw.restfullservice.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt


@Service
class TransactionService(
    val transactionRepo: TransactionRepository,
    val paymentRepo: PaymentRepository,
    val requestRepo: RequestRepository
) {

    // Get requests

    fun findTransactions(): List<Transaction> = transactionRepo.findTransactions()

    fun findTransactionByCode(code: String): Transaction {
        println("findTransactionByCode")
        return transactionRepo.findTransactionByCode(code)
    }

    fun findPayments(): List<Payment> = paymentRepo.findPayments()

    fun findPaymentsById(code: String): List<Payment> {
        println("findPaymentsById")
        val transaction: Transaction = findTransactionByCode(code)

        println("Given id: ${transaction.transaction_id}")
        return paymentRepo.findPaymentsById(transaction.transaction_id)
    }

    fun findRequests(): List<Request> = requestRepo.findRequests()

    fun findRequestsById(code: String): List<Request> {
        println("findRequestsById")
        val transaction: Transaction = findTransactionByCode(code)

        println("Given id: ${transaction.transaction_id}")
        return requestRepo.findRequestsById(transaction.transaction_id)
    }

    fun calculateTotalRequestsById(code: String): List<Request> {
        println("calculateTotalRequestsById")
        val transaction: Transaction = findTransactionByCode(code)

        /* Get all expenses per person in one list */
        println("Given id: " + transaction.transaction_code)
        val payments: List<Payment> = paymentRepo.findPaymentsById(transaction.transaction_id)

        return calculateTotals(payments)
    }

    fun calculateTotals(payments: List<Payment>): List<Request> {

        /*
        Expenses per person
            Diederich   20.00
            Edwin       35.10
            Wilco       8.00

        Total spent
                        63.10

        Average cost
                        21.03

        Balance per person
            Diederich   -1.03
            Edwin       14.06
            Wilco       -13.03

        Steps in written down below

        Wilco -> Edwin  13.03
            Diederich   -1.03
            Edwin       1.03
            Wilco       0.00

        Diederich -> Edwin 1.03
            Diederich   0.00
            Edwin       0.00
            Wilco       0.00
        */

        /* Calculate total */
        var amountSpent = .0
        payments.forEach {
            amountSpent += it.payment_amount
        }

        val amountPeople: Int = payments.size
        println("Total spent: $amountSpent with $amountPeople people")

        /* Calculate average cost */
        val sharingExpense: Double = (amountSpent / amountPeople).roundDown()
        println("Each people ownes: $sharingExpense")

        /* Calculate balance per person */
        val balance: HashMap<String, Double> = HashMap()
        payments.forEach { payment ->
            balance[payment.payment_sender_name] = (payment.payment_amount - sharingExpense).roundDown()
            println("${payment.payment_sender_name} ownes: ${balance[payment.payment_sender_name]}")
        }

        /* Calculate balance per person */
        var values = ArrayList(balance.values)
        values.sort()

        val requests = arrayListOf<Request>()
        while (Collections.frequency(values, 0.0) !== values.size) {
            val from: String = getKey(balance, values[0])!!
            val to: String = getKey(balance, values[amountPeople - 1])!!
            val amount: Double = -values[0]

            if (amount <= 0.01) {
                break
            }

            balance[to] = balance[to]!! - amount
            balance[from] = 0.0

            requests.add(
                Request(
                    -1,
                    payments.stream().filter{ from == it.payment_sender_name}.findFirst().orElse(null).transaction_id,
                    from,
                    to,
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                    payments.stream().filter{ from == it.payment_sender_name}.findFirst().orElse(null).payment_description,
                    (amount * 100.0).roundToInt() / 100.0
                )
            )

            values = ArrayList(balance.values)
            values.sort()
        }

        return requests
    }

    private fun <K, V> getKey(map: Map<K, V>, value: V): K? {
        for (key in map.keys) {
            if (value == map[key]) {
                return key
            }
        }
        return null
    }

    private fun Double.roundDown(): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(this).toDouble()
    }


    // Post requests
    fun postTransaction(transaction: Transaction) {
        println("postTransaction")
        transactionRepo.save(transaction)
    }

    fun postPayment(payment: Payment) {
        println("postPayment")
        paymentRepo.save(payment)
    }

    fun postRequest(request: Request) {
        println("postRequest")
        requestRepo.save(request)
    }

    fun postReset(code: String) {
        println("postReset")
        println("Given code: " + code.substring(0, code.length - 1))
        val transaction: Transaction = findTransactionByCode(code.substring(0, code.length - 1))
        println("Given id: " + transaction.transaction_id)
        paymentRepo.resetPayments(transaction.transaction_id)
    }
}