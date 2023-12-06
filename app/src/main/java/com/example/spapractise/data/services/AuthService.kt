package com.example.spapractise.data.services

import android.util.Log
import com.example.spapractise.data.remote.Authapi
import com.example.spapractise.data.response.Login
import com.example.spapractise.repo.AuthRepo
import com.example.spapractise.ui.auth.dataclass.User
import com.example.spapractise.utilities.Resource
import com.example.spapractise.utilities.getErrorResponseArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class AuthService constructor(
    private val authApi: Authapi
) :AuthRepo {


        override suspend fun loginUser(user: User): Flow<Resource<Login>> {

            return flow {
                emit(Resource.Loading(true))
                try {
                   Log.e("love", authApi.toString())
                    val response = authApi.login(user)
                    if (response.isSuccessful && response!=null) {
                        emit(Resource.Loading(false))
                        emit(Resource.Success(response.body()))
                    } else {
                        emit(Resource.Loading(false))
                        emit(Resource.Error(getErrorResponseArray(response.errorBody()).errors[0].toString()!!,code=response.code()))
                    }
                } catch (e: IOException) {
                    emit(Resource.Loading(false))
                    emit(Resource.Error("Something went wrong"))
                } catch (e: HttpException) {
                    emit(Resource.Loading(false))
                    emit(Resource.Error("Something went wrong"))
                }
            }
        }

}