package com.mjleeuw.restfullservice.repository

import org.springframework.web.bind.annotation.*
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.dao.IncorrectResultSizeDataAccessException

import com.mjleeuw.restfullservice.model.Transaction
import com.mjleeuw.restfullservice.model.Request
import com.mjleeuw.restfullservice.services.TransactionService

interface RequestRepository : CrudRepository<Request, String>{

    // Get requests
    
    @Query("SELECT * FROM requests")
    fun findRequests(): List<Request>

    @Query("SELECT * FROM requests r WHERE r.transaction_id = :id")
    fun findRequestsById(@Param("id") id: Int): List<Request>
}