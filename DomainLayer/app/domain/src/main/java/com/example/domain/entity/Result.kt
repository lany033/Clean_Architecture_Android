package com.example.domain.entity

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: com.example.domain.entity.UseCaseException) : Result<Nothing>()

}
