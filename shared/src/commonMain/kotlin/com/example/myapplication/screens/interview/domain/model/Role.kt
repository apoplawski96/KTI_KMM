package com.example.myapplication.screens.interview.domain.model

data class Role(
    val roleType: RoleType,
    val seniority: Seniority,
)

enum class RoleType(val displayName: String) {
    ANDROID_DEVELOPER("Android Software Engineer"),
    IOS_DEVELOPER("iOS Software Engineer");
}

enum class Seniority(val displayName: String) {
    JUNIOR(displayName = "Junior"),
    REGULAR(displayName = "Regular"),
    SENIOR(displayName = "Senior");
}