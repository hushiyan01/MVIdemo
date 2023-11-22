package com.myworkshop.myapplication.model

sealed class ResultState<T>(){
    class Loading<T>():ResultState<T>()
    class Success<T>(val body:T):ResultState<T>()
    class Error<T>(val msg:String):ResultState<T>()
}
