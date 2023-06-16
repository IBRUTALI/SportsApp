package com.example.sportsapp.presentation.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.Singletons
import com.example.sportsapp.data.room.MainDatabase
import com.example.sportsapp.domain.network.model.Result
import com.example.sportsapp.domain.room.model.ResultEntity
import com.example.sportsapp.domain.room.repository.LiveScoreRepository
import com.example.sportsapp.utils.mapToResult
import com.example.sportsapp.utils.mapToResultEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val networkRepository = Singletons.retrofitRepository
    private val roomRepository: LiveScoreRepository
    private var allData: List<Result>? = null
    val resultList: MutableLiveData<List<Result>> = MutableLiveData()

    init {
        val mainDB = MainDatabase.getDB(application)
        val liveScoreDao = mainDB.liveScoreDao()
        roomRepository = LiveScoreRepository(liveScoreDao)
    }

    private suspend fun getAll() =
        withContext(viewModelScope.coroutineContext) {
            allData = roomRepository.getAllItem().mapToResult()
    }
    fun getAllCache() {
        viewModelScope.launch {
            getAll()
            resultList.value = allData
            if (allData.isNullOrEmpty()) {
                insertInDBFromNetwork()
                _isLoading.value = false
            } else _isLoading.value = false
        }
    }

    fun insertItem(item: ResultEntity) {
        roomRepository.insertItem(item)
    }

    fun insertInDBFromNetwork() {
        viewModelScope.launch {
            getLiveScoreList()
            resultList.value?.let { results ->
                insertAllItems(results.mapToResultEntity())
            }
        }
    }

    private fun insertAllItems(item: List<ResultEntity>) {
        roomRepository.insertAllItems(item)
    }

    fun deleteItem(item: ResultEntity) {
        roomRepository.deleteItem(item)
    }

   private fun getLiveScoreList() {
       viewModelScope.launch {
           withContext(viewModelScope.coroutineContext) {
               resultList.value = networkRepository.getLiveScore().body()?.result!!
           }
       }
    }


}