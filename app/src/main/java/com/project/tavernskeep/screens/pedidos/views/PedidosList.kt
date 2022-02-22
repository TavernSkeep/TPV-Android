package com.project.tavernskeep.screens.pedidos.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.TicketModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.viewsModels.TicketViewModel

@ExperimentalMaterialApi
@Composable
fun PedidosList (navController: NavController, ticketModel: TicketViewModel) {
    LazyColumn(/*modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(1f)*/) {
        itemsIndexed(items = ticketModel.lineaTicketList) {
                _, item ->
            val state = rememberDismissState(
                confirmStateChange = {
                    if(it == DismissValue.DismissedToStart){
                        ticketModel.lineaTicketList.remove(item)
                        navController.navigate(route = "${Screen.Pedidos.route}/99")
                    }
                    true
                }
            )
            SwipeToDismiss(state = state, background = {
                val color = when (state.dismissDirection) {
                    DismissDirection.StartToEnd -> Color.Transparent
                    DismissDirection.EndToStart -> Color.Red
                    null -> MaterialTheme.colors.background
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = color)
                    .padding(10.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Borrar",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            },
                dismissContent = {PedidosItem(navController = navController, pedidosModel = item, ticketModel = ticketModel)},
                directions = setOf(DismissDirection.EndToStart))
            Divider()
        }
    }
}

