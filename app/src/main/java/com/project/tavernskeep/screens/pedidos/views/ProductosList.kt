package com.project.tavernskeep.screens.pedidos.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.tavernskeep.models.ProductosModel
import com.project.tavernskeep.viewsModels.TicketViewModel

@ExperimentalFoundationApi
@Composable
fun ProductosList (navController: NavController, productosModelList: List<ProductosModel>, categoriaClicked: Boolean, ticketModel: TicketViewModel) {
    if(!categoriaClicked){
        LazyVerticalGrid(
            cells = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(start = 30.dp, end = 30.dp, bottom = 30.dp, top = 30.dp)
        ){
            /*
            var aux: List<ProductosModel> = mutableListOf()
            if(productosModelList.isEmpty()){
                productosModelList.forEach{
                    if(it.es_categoria){
                        aux.toMutableList().add(it)
                    }
                }
            }*/
            itemsIndexed(items = productosModelList) {
                    _, item ->
                if(item.es_categoria)
                    ProductosItem(navController = navController, productosModel = item, ticketModel = ticketModel)
            }
        }
    }else{
        LazyColumn(/*modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(1f)*/) {
            itemsIndexed(items = productosModelList) {
                    _, item -> if(!item.es_categoria)ProductosItem(navController = navController, productosModel = item, ticketModel = ticketModel)
            }
        }
    }
}