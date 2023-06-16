package com.example.sportsapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportsapp.data.room.dao.LiveScoreDao
import com.example.sportsapp.domain.room.model.ResultEntity

@Database(entities = [ResultEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract fun liveScoreDao(): LiveScoreDao

    companion object {

        fun getDB(context: Context): MainDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDatabase::class.java, "main-db"
            ).build()
        }

    }
}