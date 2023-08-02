package com.example.sportsapp.presentation.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.Singletons
import com.example.sportsapp.data.room.MainDatabase
import com.example.sportsapp.domain.network.model.Result
import com.example.sportsapp.domain.room.model.ResultEntity
import com.example.sportsapp.domain.room.repository.LiveScoreRepository
import com.example.sportsapp.utils.mapToResult
import com.example.sportsapp.utils.mapToResultEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.locks.ReentrantLock

class MainViewModel(
    application: Application
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
//    private val _uiState = MutableStateFlow<List<Result>>(emptyList())
//    val uiState: StateFlow<List<Result>> = _uiState
    private val networkRepository = Singletons.retrofitRepository
    private val roomRepository: LiveScoreRepository
    private var allData: List<Result>? = null
    val resultList: MutableLiveData<List<Result>> = MutableLiveData()

    init {
        val mainDB = MainDatabase.getDB(application)
        val liveScoreDao = mainDB.liveScoreDao()
        roomRepository = LiveScoreRepository(liveScoreDao)
    }

    private suspend fun getAllAsync() =
        viewModelScope.async {
            roomRepository.getAllItem().mapToResult()
        }


    fun getAllCache() {
        viewModelScope.launch {
            allData = getAllAsync().await()
            if (allData.isNullOrEmpty()) {
                insertInDBFromNetwork()
            } else resultList.value = allData
            _isLoading.value = false
        }
    }

    fun insertItem(item: ResultEntity) {
        roomRepository.insertItem(item)
    }

    fun insertInDBFromNetwork() {
        viewModelScope.launch {
            resultList.value = getLiveScoreListAsync().await()
            resultList.value?.let { results ->
                insertAllItems(results.mapToResultEntity())
            }
            resultList.value = getAllAsync().await()
        }
    }

    private fun insertAllItems(item: List<ResultEntity>) {
        roomRepository.insertAllItems(item)
    }

    fun deleteItem(item: ResultEntity) {
        roomRepository.deleteItem(item)
    }

    private suspend fun getLiveScoreListAsync() =
        viewModelScope.async {
            networkRepository.getLiveScore().body()?.result
        }


    companion object {
        private const val LIST_KEY = "listKey"
    }

}
