package com.example.mvvmkoinsample.presentation.launchcode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmkoinsample.data.State
import com.example.mvvmkoinsample.data.remote.model.response.LaunchCode
import com.example.mvvmkoinsample.data.source.LaunchCodeSource
import kotlinx.coroutines.launch
import java.lang.Exception

class LaunchCodeViewModel(private val launchCodeSource: LaunchCodeSource) : ViewModel() {

    val launchCodeValidationState = MutableLiveData<State<LaunchCode>>()

    fun validate(code: String){
        viewModelScope.launch {
            launchCodeValidationState.postValue(State.Loading())
            try {
                val launchCode = launchCodeSource.validate(code)
                launchCodeValidationState.postValue(State.Success(launchCode))
            } catch (e : Exception) {
                launchCodeValidationState.postValue(State.Error(e))
            }
        }
    }
}