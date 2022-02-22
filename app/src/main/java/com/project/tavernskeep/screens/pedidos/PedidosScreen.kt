package com.project.tavernskeep.screens.pedidos

import android.content.res.Configuration
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.models.ProductosModel
import com.project.tavernskeep.models.TicketModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.screens.pedidos.views.PedidosList
import com.project.tavernskeep.screens.pedidos.views.ProductosList
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.viewsModels.MesasViewModel
import com.project.tavernskeep.viewsModels.ProductosViewModel
import com.project.tavernskeep.viewsModels.TicketViewModel
import java.lang.StringBuilder
import kotlin.random.Random

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PedidosScreen(navController: NavController, productosModel : ProductosViewModel, ticketModel: TicketViewModel, context: Configuration, tOrP: String, mesasModel: MesasViewModel) {
    val chars = tOrP.toCharArray()
    var i = 0
    for (c in chars) {
        if (Character.isDigit(c)) {
        }else{
            i++
        }
    }
    if(tOrP != "uwu")
    productosModel.categoriaClicked = (i > 1)
    /*
    if(i == 0){
        productosModel.get1Producto(tOrP)
        if(productosModel.lin.nombre != "jijijija")
            ticketModel.ticketModel.listaproductos.add(ticketModel.ticketModel.listaproductos.lastIndex+1, productosModel.lin)
    }*/
    Scaffold(topBar ={
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Para volver atrás.", tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clickable { refreshMesas(navController, ticketModel, productosModel) }
                .fillMaxHeight(1f)
                .fillMaxWidth(0.13f))
            if(context.orientation == Configuration.ORIENTATION_PORTRAIT)
                Spacer(modifier = Modifier.width(50.dp))
            else
                Spacer(modifier = Modifier.width(210.dp))
            Text(text = "Pedidos", style = TextStyle(fontFamily = Arcade),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontSize = 25.sp)
            if(context.orientation == Configuration.ORIENTATION_PORTRAIT)
                Spacer(modifier = Modifier.width(50.dp))
            else
                Spacer(modifier = Modifier.width(200.dp))
            Icon(imageVector = Icons.Default.Done,
            contentDescription = "Añadir el pedido a la mesa",tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .clickable { /*ticketModel.actualizeTicket(ticketModel.ticketModel.codigo)*/
                    val y = Random.nextInt(0, 999999999).toString()
                                if(mesasModel.editandoMesas) {
                                    ticketModel.uploadTicket(
                                        TicketModel(_id = "T$y",
                                            "T$y", mesa = mesasModel.mesasModel._id, ticketModel.lineaTicketList, ticketModel.currentDate))
                                    if(ticketModel.lineaTicketList.isEmpty()){
                                        mesasModel.mesasModel.ticket_actual = "uwu"
                                    }else {
                                        mesasModel.mesasModel.ticket_actual = y
                                    }
                                    mesasModel.actualizeMesa(MesasModel(_id = mesasModel.mesasModel._id, zona = mesasModel.mesasModel.zona, n_sillas = mesasModel.mesasModel.n_sillas, is_reservada = mesasModel.mesasModel.is_reservada, ticket_actual = "T"+mesasModel.mesasModel.ticket_actual, codigo = mesasModel.mesasModel.codigo))
                                }else{
                                    ticketModel.actualizeTicket(TicketModel(_id = ticketModel.ticketModel._id, codigo = ticketModel.ticketModel.codigo, mesa = mesasModel.mesasModel._id, listaproductos = ticketModel.lineaTicketList, fecha = ticketModel.currentDate))
                                    if(ticketModel.lineaTicketList.isEmpty()){
                                        mesasModel.mesasModel.ticket_actual = "uwu"
                                        mesasModel.actualizeMesa(MesasModel(_id = mesasModel.mesasModel._id, zona = mesasModel.mesasModel.zona, n_sillas = mesasModel.mesasModel.n_sillas, is_reservada = mesasModel.mesasModel.is_reservada, ticket_actual = mesasModel.mesasModel.ticket_actual, codigo = mesasModel.mesasModel.codigo))
                                    }
                                    }
                                }
                .fillMaxHeight(1f)
                .fillMaxWidth(1f))
        }
    }) {
        /*
        var aux: List<ProductosModel> = mutableListOf()
        if (productosModel.categoriaClicked){
            productosModel.productosModelListResponse.forEach{
                if(!it.es_categoria)
                    aux.toMutableList().add(it)
            }
        }else{
            productosModel.productosModelListResponse.forEach{
                if(it.es_categoria)
                    aux.toMutableList().add(it)
            }
        }*/
        if(!productosModel.categoriaClicked)
            bodyContent(navController,  productosModel.categoriasList, productosModel.categoriaClicked, ticketModel, context)
        else
            bodyContent(navController,  productosModel.productosList, productosModel.categoriaClicked, ticketModel, context)
    }
}

fun refreshMesas(navController: NavController, ticketModel: TicketViewModel, productosModel: ProductosViewModel) {
    //ticketModel.ticketModel.listaproductos.clear()
    navController.navigate(route = Screen.Mesas.route)
    productosModel.categoriaClicked = false
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun bodyContent(navController: NavController, productosModelList : List<ProductosModel>, categoriaClicked: Boolean, ticketModel: TicketViewModel, context: Configuration) {
    Column(modifier = Modifier.fillMaxSize(1f)){
        Surface(color = MaterialTheme.colors.background, modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)) {
            PedidosList(navController = navController,  ticketModel = ticketModel)
        }
        Row(modifier = Modifier
            .fillMaxWidth(1f)
            .height(35.dp)
            .wrapContentSize(align = Alignment.Center)){
            Column() {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Categorías/Productos", style = TextStyle(fontFamily = Arcade),
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp)
            }
            if(context.orientation == Configuration.ORIENTATION_PORTRAIT)
                Spacer(modifier = Modifier.width(5.dp))
            else
                Spacer(modifier = Modifier.width(5.dp))
            if(categoriaClicked)
            Icon(imageVector = Icons.Default.Refresh,
                contentDescription = "Volver a categorías",tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .clickable { navController.navigate(route = "${Screen.Pedidos.route}/a99") }
                    .width(50.dp)
                    .height(50.dp))
        }
        Surface(color = MaterialTheme.colors.background, modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)) {
            ProductosList(navController = navController, productosModelList = productosModelList, categoriaClicked = categoriaClicked, ticketModel = ticketModel)
        }
    }
}