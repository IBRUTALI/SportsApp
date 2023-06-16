package com.example.sportsapp.data.room.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LiveScoreDao {
    @Query("SELECT * FROM ")
    fun getAll()

}