package com.example.spapractise.data.remote

import com.example.spapractise.URL
import com.example.spapractise.data.response.Login
import com.example.spapractise.ui.auth.dataclass.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface Authapi {

    @POST(URL.LOGIN)
    suspend fun login(@Body user: User): Response<Login>
}