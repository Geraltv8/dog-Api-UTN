package com.profegera.dogapi.model

data class DogResponse(
    val message: String,
    val status: String
)

data class DogResponseList(
    val message: List<String>,
    val status: String
)