package com.example.spapractise.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spapractise.URL
import com.example.spapractise.data.remote.Authapi
import com.example.spapractise.data.response.Login
import com.example.spapractise.repo.AuthRepo
import com.example.spapractise.ui.auth.dataclass.User
import com.example.spapractise.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepo
):ViewModel() {


    val authapi:Authapi= Retrofit.Builder()
        .baseUrl(URL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Authapi::class.java)

    private val _loginUser = MutableSharedFlow<Resource<Login>>()
    val loginUser = _loginUser.asSharedFlow()
     fun loginUser(user: User){
      //  var authRepository=AuthService(authapi)
        viewModelScope.launch {
            authRepository.loginUser(user).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        result.message?.let {
                            _loginUser.emit(Resource.Error(result.message,code=result.code))
                        }
                    }
                    is Resource.Loading -> {
                        _loginUser.emit(Resource.Loading(result.isLoading))
                    }
                    is Resource.Success -> {
                        _loginUser.emit(Resource.Success(result.data))
                    }
                }
                Log.e("TAG", "login: $result")
            }
        }
    }
}