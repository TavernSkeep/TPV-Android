package com.project.tavernskeep.models

data class EmployeeModel(
    val _id: String,
    val dni: String,
    val contraseña: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String,
    val puesto: String,
    val email: String
)