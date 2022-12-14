package com.gabrielbmoro.programmingchallenge.domain.model

data class DataOrException<T, E : Exception>(
    val data: T? = null,
    val exception: E? = null
)