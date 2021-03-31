package com.mjleeuw.restfullservice.model

data class ErrorResponse (
    val code: Int,
    val error: String
)

enum class ErrorType(val code: Int, val message: String) {
    UNKNOWN_ERROR(0, "There is no specific error defined."),
    INVALID_PATH(1, "The requested path isn't available."),
    NO_TRANSACTIONS_FOUND(2, "No transactions found based on requested code."),
    INVALID_POST_OBJECT(3, "The posted object doesn't meet the requirements.")
}