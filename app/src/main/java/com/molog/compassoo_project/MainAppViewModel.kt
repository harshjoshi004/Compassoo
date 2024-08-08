package com.molog.compassoo_project

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainAppViewModel : ViewModel() {
    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.ParentNavigatorScreen.ParentHomeScreen)
    val currentScreen : MutableState<Screen> = _currentScreen
    private val _currentNavigation : MutableState<Screen> = mutableStateOf(Screen.Navigation.SignInNavigation)
    val currentNavigation : MutableState<Screen> = _currentNavigation
    private val _signInDone : MutableState<Boolean> = mutableStateOf(false)
    val signInDone : MutableState<Boolean> = _signInDone
    private val _showAppBars : MutableState<Boolean> = mutableStateOf(false)
    val showAppBars: MutableState<Boolean> = _signInDone


    fun setAppBars(bool:Boolean){
        _showAppBars.value = bool
    }
    fun setCurrentScreen(screen : Screen){
        _currentScreen.value = screen
    }
    fun setCurrentNavigation(navigation : Screen.Navigation){
        _currentNavigation.value = navigation
    }
    fun signInDone(){
        _signInDone.value = true
    }
}