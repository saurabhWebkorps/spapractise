package com.example.spapractise.network

import android.content.Context
import com.example.spapractise.URL
import com.example.spapractise.data.remote.Authapi
import com.example.spapractise.data.services.AuthService
import com.example.spapractise.repo.AuthRepo
import com.example.spapractise.utilities.core.Session
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)


    @Provides
    fun provideAuthAPI(): Authapi {
        return Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authApi: Authapi): AuthRepo {
        return AuthService(authApi)
    }
    @Singleton
    @Provides
    fun provideSession(@ApplicationContext context: Context): Session {
        return Session(context)
    }
}