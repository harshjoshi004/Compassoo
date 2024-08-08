package com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer

import com.molog.compassoo_project.ApiService
import retrofit2.Response

class UserRepository(private val apiService: ApiService){
    suspend fun postUserData(userData: UserData): Response<Void> {
        return apiService.postUserData(userData)
    }
}