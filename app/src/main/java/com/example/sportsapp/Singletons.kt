package com.example.sportsapp

import com.example.sportsapp.data.network.retrofit.RetrofitRepository
import com.example.sportsapp.domain.network.retrofit.RetrofitRepositoryImpl

object Singletons {

    val retrofitRepository: RetrofitRepository by lazy {
        RetrofitRepositoryImpl()
    }

}