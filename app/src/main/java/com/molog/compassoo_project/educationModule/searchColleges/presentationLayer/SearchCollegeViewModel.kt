package com.molog.compassoo_project.educationModule.searchColleges.presentationLayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molog.compassoo_project.RetrofitClient
import com.molog.compassoo_project.educationModule.searchColleges.domainLayer.CollegeDetailsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchCollegeViewModel : ViewModel() {
    private val _collegeDetails = MutableStateFlow<CollegeDetailsResponse?>(null)
    val collegeDetails: StateFlow<CollegeDetailsResponse?> = _collegeDetails

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val searchResultModifierList = mutableListOf<String?>()

    fun searchColleges(course: String?, specialization: String?) {
        _loading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCollegeDetails(course, specialization)
                println("Search College viewModel: ${response.body()}")
                if (response.isSuccessful) {
                    _collegeDetails.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}