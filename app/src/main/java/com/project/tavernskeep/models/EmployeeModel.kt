package com.project.tavernskeep.models

data class EmployeeModel(
    val dni: String,
    val password: String,
    val name: String,
    val lastName: String,
    val phoneNumber: Int,
    val rol: String,
    val email: String
)