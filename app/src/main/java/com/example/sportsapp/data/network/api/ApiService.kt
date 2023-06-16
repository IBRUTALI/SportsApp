package com.example.sportsapp.data.network.api

import com.example.sportsapp.API_KEY
import com.example.sportsapp.domain.network.model.LiveScore
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("?met=Livescore&APIkey=$API_KEY")
    suspend fun getLiveScore(): Response<LiveScore>
}