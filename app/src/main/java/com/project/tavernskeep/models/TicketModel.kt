package com.project.tavernskeep.models


data class TicketModel (
    val _id: String,
    val codigo: String,
    val mesa: String,
    var listaproductos: MutableList<LineaTicketModel>,
    val fecha: String
)