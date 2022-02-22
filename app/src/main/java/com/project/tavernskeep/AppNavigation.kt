package com.project.tavernskeep

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.models.ProductosModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.screens.mesas.MesasScreen
import com.project.tavernskeep.screens.pedidos.PedidosScreen
import com.project.tavernskeep.viewsModels.EmployeeViewModel
import com.project.tavernskeep.viewsModels.MesasViewModel
import com.project.tavernskeep.viewsModels.ProductosViewModel
import com.project.tavernskeep.viewsModels.TicketViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AppNavigation(mesasModel: MesasViewModel, productosModel: ProductosViewModel, ticketModel: TicketViewModel, context: Configuration, employeeModel: EmployeeViewModel, context2: Context){
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = Screen.Mesas.route){
        composable(route = Screen.Mesas.route){
            mesasModel.getMesasList()
            MesasScreen(navController = navController, mesasModel = mesasModel, context = context, employeeModel = employeeModel, context2 = context2)
        }
        composable(route = Screen.Pedidos.route+ "/{id}",
            arguments = listOf(
                navArgument(name = "id") {type = NavType.StringType}
            )){
                val id = it.arguments?.getString("id")
                requireNotNull("id")
                val chars = id?.toCharArray()
                var i = 0
                for (c in chars!!) {
                    if (Character.isDigit(c))
                        i++
                }
                productosModel.getProductosList(id!!)
                if(i == 0){
                    if(id == "uwu"){
                        ticketModel.lineaTicketList.clear()
                        ticketModel.ticketModel._id = ""
                    Log.d("CLEAR AQUÍ", ticketModel.ticketModel.toString())}
                }
                else if(i > 2) {
                    //ticketModel.ticketModel.listaproductos.clear()
                        if(ticketModel.ticketModel._id != mesasModel.mesasModel.ticket_actual){
                            ticketModel.get1Ticket(id!!)
                    Log.d("ESTOY HACIENDO EL GET", ticketModel.ticketModel.toString())}
                }
                //Log.d("TICKET CARGADO", ticketModel.ticketModel.toString())
                //Log.d("LISTA DE PRODUCTOS", ticketModel.ticketModel.listaproductos.toString())
                //Log.d("TICKET ACUAL DE MESA", mesasModel.mesasModel.ticket_actual)
                    /*
                Log.d("TICKET ACUAL DE MESA", it.arguments?.getString("id")!!)
                Log.d("ID ACTUAL", it.arguments?.getString("id")!!)*/
                //SI LA ID ES "UWU" LA MESA ESTÁ LIBRE
                //Log.d("LINEA TICKET", ticketModel.ticketModel.listaProductos.toString())
                //Log.d("TICKET CARGADO", ticketModel.ticketModel.toString())
                PedidosScreen(navController = navController, productosModel= productosModel, ticketModel = ticketModel, context = context, tOrP = id, mesasModel = mesasModel)
        }
    }
}