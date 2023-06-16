package com.example.sportsapp.data.room.dao

import androidx.room.*
import com.example.sportsapp.domain.room.model.ResultEntity

@Dao
interface LiveScoreDao {
    @Query("SELECT * FROM results")
    fun getAll(): List<ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(item: ResultEntity)

    @Delete
    fun deleteData(item: ResultEntity)

}