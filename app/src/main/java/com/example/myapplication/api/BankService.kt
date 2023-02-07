package com.example.myapplication.api

import com.example.myapplication.data.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BankService {
    @GET("/{number}")
    suspend fun getData(@Path("number") number: String) : Response<Data>
}