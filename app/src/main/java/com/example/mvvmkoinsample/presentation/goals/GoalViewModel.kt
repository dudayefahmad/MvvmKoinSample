package com.example.mvvmkoinsample.presentation.goals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmkoinsample.data.State
import com.example.mvvmkoinsample.data.remote.model.response.Goals
import com.example.mvvmkoinsample.data.source.GoalDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class GoalViewModel(private val goalDataSource: GoalDataSource) : ViewModel() {

    val getGoalListState = MutableLiveData<State<List<Goals>>>()

    init {
        getGoalList()
    }

    private fun getGoalList() {
        getGoalListState.postValue(State.Loading())
        viewModelScope.launch {
            try {
                val goals = goalDataSource.getGoalList()
                delay(500)
                getGoalListState.postValue(State.Success(goals))
            } catch (e: Exception) {
                getGoalListState.postValue(State.Error(e))
            }
        }
    }

}