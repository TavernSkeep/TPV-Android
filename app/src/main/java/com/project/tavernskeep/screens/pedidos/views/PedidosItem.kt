package com.project.tavernskeep.screens.pedidos.views

import com.project.tavernskeep.models.LineaTicketModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.project.tavernskeep.R
import com.project.tavernskeep.loginClicked
import com.project.tavernskeep.models.TicketModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import com.project.tavernskeep.viewsModels.TicketViewModel

@Composable
fun PedidosItem(navController: NavController, pedidosModel: LineaTicketModel, ticketModel: TicketViewModel) {
    /*
    var auxColor: Color
    var auxImg: Int

    if(pedidosModel.es_categoria){
        auxColor = colorResource(id = R.color.orange)
        auxImg = R.drawable.sword_reservado
    }else if(pedidosModel.tipo_producto == ""){
        auxColor = colorResource(id = R.color.green)
        auxImg = R.drawable.sword_libre
    }else{
        auxColor = colorResource(id = R.color.red)
        auxImg = R.drawable.sword_ocupado
    }*/

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(60.dp)
        .background(MaterialTheme.colors.background),

        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .padding(4.dp)
                .background(MaterialTheme.colors.background)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center) {
                /*
                Image(
                    painter = rememberImagePainter(
                        data = pedidosModel.imagen,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.notification_action_background)
                            transformations(CircleCropTransformation())
                        },
                    ),
                    contentDescription = pedidosModel.especificaciones,
                    modifier =  Modifier.fillMaxHeight()
                        .weight(0.2f),
                )*/
                Row() {
                    Text(
                        text = pedidosModel.nombre,
                        style = TextStyle(fontFamily = Arcade),
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Uds:" + pedidosModel.cantidad.toString(),
                        style = TextStyle(fontFamily = Arcade),
                        color = colorResource(id = R.color.orange),
                        fontSize = 12.sp/*
                    modifier = Modifier
                        .background(
                            Color.White
                        )
                        .padding(4.dp),*/
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row() {
                    Text(
                        text = "Precio unidad: " + pedidosModel.precioUnidad + "€",
                        style = TextStyle(fontFamily = Arcade),/*
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,*/
                        color = Color.Blue,
                        fontSize = 7.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Importe: " + pedidosModel.precioTotal + "€",
                        style = TextStyle(fontFamily = Arcade),/*
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,*/
                        color = colorResource(id = R.color.green),
                        fontSize = 7.sp
                    )
                }
            }
            Column() {
                Button(
                    modifier = Modifier.height(30.dp).width(50.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(),
                    onClick = {

                                pedidosModel.cantidad++
                                pedidosModel.precioTotal = pedidosModel.cantidad * pedidosModel.precioUnidad
                                navController.navigate(route = "${Screen.Pedidos.route}/99")}){
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colors.primaryVariant,
                                        MaterialTheme.colors.primary
                                    )
                                )
                            )
                            .padding(horizontal = 11.dp, vertical = 9.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "+", color = Color.White, style = TextStyle(fontFamily = Arcade))
                    }
                }
                Button(
                    modifier = Modifier.height(30.dp).width(50.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(),
                    onClick = {
                                if(pedidosModel.cantidad > 1){
                                    pedidosModel.cantidad--
                                }
                                pedidosModel.precioTotal = pedidosModel.cantidad * pedidosModel.precioUnidad
                                navController.navigate(route = "${Screen.Pedidos.route}/99")}){
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colors.primaryVariant,
                                        MaterialTheme.colors.primary
                                    )
                                )
                            )
                            .padding(horizontal = 14.dp, vertical = 9.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "‒", color = Color.White, style = TextStyle(fontFamily = Arcade), fontSize = 15.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TavernSkeepTheme {
        val pedidos = LineaTicketModel("a", 2, 6.00, 12.0)
        PedidosItem(navController = rememberNavController(), pedidosModel = pedidos, ticketModel = TicketViewModel())
    }
}