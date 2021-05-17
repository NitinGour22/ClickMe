package com.piexxi.clickme

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val timer: CountDownTimer

    //setup Live Data
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> get() = _currentTime

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _currentButton = MutableLiveData<Int>()
    val currentButton: LiveData<Int> get() = _currentButton

    private val _scoreColor = MutableLiveData<String>()
    val scoreColor: LiveData<String> get() = _scoreColor

    private val _gameFinish = MutableLiveData<Boolean>()
    val gameFinish: LiveData<Boolean> get() = _gameFinish

    //set liveData values , set up ans start timer
    init {
        _score.value = 0
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
        _gameFinish.value = false

        //set up the timer
        timer = object : CountDownTimer(31000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / 1000
                _scoreColor.value = "purple"

            }

            override fun onFinish() {
                _gameFinish.value = true
            }

        }

        //start the timer
        timer.start()
    }

    fun gainPoint() {
        _score.value = _score.value?.plus(1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "purple"
    }

    fun loosPoint() {
        _score.value = _score.value?.plus(-1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "red"
    }
}