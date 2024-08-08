package com.molog.compassoo_project.educationModule.searchColleges.domainLayer

import com.google.gson.annotations.SerializedName

data class CollegeDetailsResponse(
    @SerializedName("filteredColleges")
    val collegeDetails: List<CollegeDetail>
)

data class CollegeDetail(
    @SerializedName("_id")
    val id: String,
    @SerializedName("university_name")
    val universityName: String,
    @SerializedName("courses")
    val courses: List<Course?>
)

data class Course(
    @SerializedName("_id")
    val id: String,
    @SerializedName("courseTitle")
    val courseTitle: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("modeOfStudy")
    val modeOfStudy: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("studyType")
    val studyType: String,
    @SerializedName("examScores")
    val examScores: List<ExamScore>,
    @SerializedName("importantDate")
    val importantDate: String,
    @SerializedName("fees")
    val fees: Fees
)

data class ExamScore(
    @SerializedName("_id")
    val id: String,
    @SerializedName("examName")
    val examName: String,
    @SerializedName("examScore")
    val examScore: String
)

data class Fees(
    @SerializedName("INR")
    val inr: String,
    @SerializedName("CAD")
    val cad: String,
    @SerializedName("_id")
    val id: String
)
