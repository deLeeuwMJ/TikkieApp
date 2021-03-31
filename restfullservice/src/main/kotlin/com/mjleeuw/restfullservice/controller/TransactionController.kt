package com.mjleeuw.restfullservice.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.omg.CORBA.UNKNOWN

import com.mjleeuw.restfullservice.model.*
import com.mjleeuw.restfullservice.services.TransactionService

@RestController
class TransactionController(val service: TransactionService) {

    @RequestMapping("/")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun invalidPath() : ErrorResponse{
        return ErrorResponse(
            ErrorType.UNKNOWN_ERROR.code,
            ErrorType.UNKNOWN_ERROR.message
            );
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun invalidSpecificPath() : ErrorResponse{
        return ErrorResponse(
            ErrorType.INVALID_PATH.code,
            ErrorType.INVALID_PATH.message
            );
    }
    
    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    fun getTransactions(): List<Transaction> = service.findTransactions()

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTransactionByCode(@PathVariable("id") code: String): Any {
        var transaction: Transaction = service.findTransactionByCode(code)
        return transaction
    }

    @PostMapping("/transactions/reset")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun resetTransactions() = service.resetAllTransactions()

    @PostMapping("/transactions/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody transaction: Transaction) {
        service.post(transaction)
    }
}
