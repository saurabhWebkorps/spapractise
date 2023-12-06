package com.example.spapractise.repo

import com.example.spapractise.data.response.Login
import com.example.spapractise.ui.auth.dataclass.User
import com.example.spapractise.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    suspend fun loginUser(user: User) : Flow<Resource<Login>>

}