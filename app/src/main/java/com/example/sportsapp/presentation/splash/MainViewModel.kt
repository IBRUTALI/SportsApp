package com.example.sportsapp.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.Singletons
import com.example.sportsapp.domain.network.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val repository = Singletons.retrofitRepository
    val resultList: MutableLiveData<List<Result>> = MutableLiveData()

    fun getLiveScoreList() {
        viewModelScope.launch {
            resultList.value = repository.getLiveScore().body()?.result!!
            _isLoading.value = false
        }
    }

}