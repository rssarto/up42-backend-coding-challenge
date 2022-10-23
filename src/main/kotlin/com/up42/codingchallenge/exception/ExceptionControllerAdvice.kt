package com.up42.codingchallenge.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity(ErrorMessageModel(HttpStatus.NOT_FOUND.value(), ex.message), HttpStatus.NOT_FOUND)
    }
}
