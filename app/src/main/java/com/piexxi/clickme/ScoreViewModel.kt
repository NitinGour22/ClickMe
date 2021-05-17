package com.piexxi.clickme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(private val score : Int) : ViewModel() {

    //set up live data
    private val _finalScore =MutableLiveData<Int>()
    val finalscore : LiveData<Int> get() = _finalScore

    private val _hasGambled = MediatorLiveData<Boolean>()
    val hasGambled : LiveData<Boolean> get() = _hasGambled

    init{
        _finalScore.value = score
    }

    // User wants to gamble his points
    fun gamble(){
        val swing = (0..5).random()
        _finalScore.value = _finalScore.value?.plus(swing)
        _hasGambled.value = true
    }

}