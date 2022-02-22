package com.project.tavernskeep.viewsModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.ProductosModel
import com.project.tavernskeep.services.ApiServices
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductosViewModel : ViewModel(){
    var productosModelListResponse : List<ProductosModel> by mutableStateOf(listOf())
    var categoriasList : List<ProductosModel> by mutableStateOf(listOf())
    var productosList : List<ProductosModel> by mutableStateOf(listOf())
    var productosModel : ProductosModel by mutableStateOf(ProductosModel("uwu", "uwu", "uwu", 12.0, "uwu", 8, "uwu", true))
    var categoriaClicked by mutableStateOf(false)
    var lin : LineaTicketModel by mutableStateOf(LineaTicketModel("jijijija", 1, 2.00, 2.00))
    private var errorMessage : String by mutableStateOf("")

    fun getProductosList(nombre: String) {
        viewModelScope.launch{
            val apiServices = ApiServices.getInstance()
            try {
                val productosList1 = apiServices.getProductos()
                if(productosList1.isSuccessful){
                    productosModelListResponse = productosList1.body()!!
                    if (!categoriaClicked){
                        productosModelListResponse.forEach {
                            if(it.es_categoria && it.nombre != "Todo")
                                if(!categoriasList.contains(it))
                                    categoriasList += it
                                //categoriasList.toMutableList().add(it)
                        }
                    }else{
                        if(productosList.isNotEmpty())
                            productosList = mutableListOf()
                        productosModelListResponse.forEach {
                            if(!it.es_categoria && it.tipo_producto == nombre)
                                if(!productosList.contains(it))
                                    productosList += it
                                //productosList.toMutableList().add(it)
                        }
                    }
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1Producto(codigo: String) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val productos1 = apiServices.getProducto(codigo)
                if(productos1.isSuccessful){
                    productosModel = productos1.body()!!
                    lin.nombre = productosModel.nombre
                    lin.precioUnidad = productosModel.precio
                    lin.cantidad = 1
                    lin.precioTotal = productosModel.precio
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}