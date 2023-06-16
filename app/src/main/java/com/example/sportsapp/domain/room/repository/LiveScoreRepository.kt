package com.example.sportsapp.domain.room.repository

import com.example.sportsapp.data.room.dao.LiveScoreDao
import com.example.sportsapp.domain.room.model.ResultEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LiveScoreRepository(private val liveScoreDao: LiveScoreDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun getAllItem(): List<ResultEntity> {
       return withContext(coroutineScope.coroutineContext) {
           liveScoreDao.getAll()
       }
    }

    fun insertItem(item: ResultEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            liveScoreDao.insertData(item)
        }
    }

    fun insertAllItems(items: List<ResultEntity>) {
        coroutineScope.launch(Dispatchers.IO) {
            items.forEach {item ->
                liveScoreDao.insertData(item)
            }
        }
    }

    fun deleteItem(item: ResultEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            liveScoreDao.deleteData(item)
        }
    }

}