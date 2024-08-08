package com.molog.compassoo_project.educationModule.createEducationAccount.domainLayer

data class UserData(
    val name: String,
    val email: String,
    val profilePicture: String,
    val phone: String,
    val exams: List<String>,
    val workExperience: Int,
    val qualifications: List<String>,
    val userPR: Boolean,
    val language: List<String>,
    val resume: String
)
