package com.molog.compassoo_project.parentModule.signIn

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel:ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    private val _myUserData = mutableStateOf<GoogleAuthUiClient.MyUserData?>(null)
    val myUserData : MutableState<GoogleAuthUiClient.MyUserData?> = _myUserData

    fun setMyUserData(value: GoogleAuthUiClient.MyUserData?) {
        _myUserData.value = value
    }

    fun isMyUserDataNull() : Boolean {
        println("Harsh: UserData is ${_myUserData.value.toString()}")
        return _myUserData.value == null
    }

    fun onSignInResult(result: GoogleAuthUiClient.SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState(){
        _state.update { SignInState() }
    }
}