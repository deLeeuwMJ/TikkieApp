package com.mjleeuw.restfullservice.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.omg.CORBA.UNKNOWN

import com.mjleeuw.restfullservice.model.*
import com.mjleeuw.restfullservice.services.TransactionService

@RestController
class TransactionController(val service: TransactionService) {

    // Get request

    @CrossOrigin("http://localhost:4200")
    @RequestMapping("/")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun invalidPath() : ErrorResponse{
        return ErrorResponse(
            ErrorType.UNKNOWN_ERROR.code,
            ErrorType.UNKNOWN_ERROR.message
            );
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun invalidSpecificPath() : ErrorResponse{
        return ErrorResponse(
            ErrorType.INVALID_PATH.code,
            ErrorType.INVALID_PATH.message
            );
    }
    
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    fun getTransactions(): List<Transaction> = service.findTransactions()

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTransactionByCode(@PathVariable("id") code: String): Any {
        var transaction: Transaction = service.findTransactionByCode(code)
        return transaction
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    fun getPayments(): List<Payment> = service.findPayments()

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPaymentsById(@PathVariable("id") code: String): Any {
        var payments: List<Payment> = service.findPaymentsById(code)
        return payments
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    fun getRequests(): List<Request> = service.findRequests()

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/requests/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getRequestsById(@PathVariable("id") code: String): Any {
        var requests: List<Request> = service.findRequestsById(code)
        return requests
    }

    // Post request

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/transactions/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTransaction(@RequestBody transaction: Transaction) {
        service.postTransaction(transaction)
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/payments/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun postPayment(@RequestBody payment: Payment) {
        service.postPayment(payment)
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/requests/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun postRequest(@RequestBody request: Request) {
        service.postRequest(request)
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/payments/reset") 
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun postAction(@RequestBody code: String) {
        service.postReset(code)
    }
}
