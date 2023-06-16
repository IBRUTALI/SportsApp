package com.example.sportsapp.domain.network.retrofit

import com.example.sportsapp.data.network.retrofit.RetrofitInstance
import com.example.sportsapp.data.network.retrofit.RetrofitRepository
import com.example.sportsapp.domain.network.model.LiveScore
import retrofit2.Response

class RetrofitRepositoryImpl: RetrofitRepository {

    override suspend fun getLiveScore(): Response<LiveScore> {
        return RetrofitInstance.api.getLiveScore()
    }

}