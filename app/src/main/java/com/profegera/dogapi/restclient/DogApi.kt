package com.profegera.dogapi.restclient

import com.profegera.dogapi.model.DogResponse
import com.profegera.dogapi.model.DogResponseList
import retrofit2.Call
import retrofit2.http.GET

interface DogApi {
    @GET("breeds/image/random")
    fun getRandomDogImage(): Call<DogResponse>

    @GET("breed/hound/images")
    fun getListOfBreeds(): Call<DogResponseList>
}