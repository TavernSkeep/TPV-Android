package com.project.tavernskeep.models

data class MesasModel (
    val _id: String,
    var codigo: String,
    var zona: String,
    var n_sillas: Int,
    var is_reservada: Boolean,
    var ticket_actual: String
)