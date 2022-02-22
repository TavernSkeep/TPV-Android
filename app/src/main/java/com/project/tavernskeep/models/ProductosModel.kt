package com.project.tavernskeep.models

data class ProductosModel(
    val _id: String,
    val nombre: String,
    val especificaciones: String,
    val precio: Double,
    val imagen: String,
    val stock: Int,
    val tipo_producto: String,
    val es_categoria: Boolean
)