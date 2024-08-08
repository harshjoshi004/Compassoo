package com.molog.compassoo_project.educationModule.createEducationAccount.presentationLayer

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molog.compassoo_project.RetrofitClient
import com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer.UserData
import com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccountViewModel:ViewModel() {
    // 100 - no response, 200 - error, 401 - Already Account exists, else - string to show in popUp
    var statusMessageState = MutableStateFlow<String>("0")

    fun resetState(){
        statusMessageState.value = "0"
    }

    fun postUserData(userData: UserData) : String {
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.apiService
                val userRepository = UserRepository(apiService)
                val response = userRepository.postUserData(userData)

                if (response.isSuccessful) {
                    println("Retrofit: Data posted successfully, code: ${response.code()}")
                    statusMessageState.value = "100"
                } else if (response.code() == 401) {
                    println("Retrofit: User already exist, Welcome!")
                    statusMessageState.value = "401"
                } else {
                    println("Retrofit: Failed to post data, code: ${response.code()}")
                    statusMessageState.value = "200"
                }
            } catch (e: Exception) {
                println("Retrofit: Exception: ${e.message}")
                statusMessageState.value = "200"
            }
        }
        return statusMessageState.value
    }
}