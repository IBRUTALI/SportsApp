package com.example.sportsapp.data.network.retrofit

import com.example.sportsapp.domain.network.model.LiveScore
import retrofit2.Response

interface
RetrofitRepository {

    suspend fun getLiveScore(): Response<LiveScore>

}