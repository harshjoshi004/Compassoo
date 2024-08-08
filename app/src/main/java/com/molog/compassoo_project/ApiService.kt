package com.molog.compassoo_project

import com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer.UserData
import com.molog.compassoo_project.educationModule.searchColleges.domainLayer.CollegeDetailsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("education/query")
    suspend fun postUserData(@Body userData: UserData): Response<Void>

    @GET("education/college")
    suspend fun getCollegeDetails(
        @Query("course") course: String?,
        @Query("specialization") specialization: String?
    ): Response<CollegeDetailsResponse>
}