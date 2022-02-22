package com.project.tavernskeep.screens.pedidos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.ProductosModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import com.project.tavernskeep.viewsModels.TicketViewModel

@Composable
fun ProductosItem(navController: NavController, productosModel: ProductosModel, ticketModel: TicketViewModel) {

    if(productosModel.es_categoria){
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(110.dp)
                .clickable { navController.navigate(route = "${Screen.Pedidos.route}/${productosModel.nombre}") },

            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(colorResource(id = R.color.orange)),
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = productosModel.imagen,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.notification_action_background)
                            transformations(CircleCropTransformation())
                        },
                    ),
                    contentDescription = productosModel.especificaciones,
                    modifier = Modifier
                        .fillMaxHeight(1f)
                )
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = productosModel.nombre,
                        style = TextStyle(fontFamily = Arcade),
                        color = Color.White,
                        fontSize = 12.sp)
                }
            }
        }
    }else {
            Card(
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .height(110.dp)
                    .clickable {
                                    var l = LineaTicketModel(productosModel.nombre, 1, productosModel.precio, productosModel.precio)
                                    ticketModel.lineaTicketList.add(0, l)
                                    navController.navigate(route = "${Screen.Pedidos.route}/99")
                               },

                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                        .background(colorResource(id = R.color.green)),
                ) {
                    Image(
                        painter = rememberImagePainter(data = productosModel.imagen),
                        //contentScale = ContentScale.Crop,
                        contentDescription = productosModel.especificaciones,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.4f)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.8f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = productosModel.nombre,
                            style = TextStyle(fontFamily = Arcade),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Stock: " + productosModel.stock,
                            style = TextStyle(fontFamily = Arcade),
                            color = Color.White,/*
                    modifier = Modifier
                        .background(
                            Color.White
                        )
                        .padding(4.dp),*/
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Precio: " + productosModel.precio + "€",
                            style = TextStyle(fontFamily = Arcade),/*
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,*/
                            color = Color.Yellow,
                        )
                    }
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TavernSkeepTheme {
        val productos = ProductosModel("a", "mesa1", "Comedor principal", 12.0, "https://www.linuxadictos.com/wp-content/uploads/balrum.jpg", 8, "uwu", false)
        ProductosItem(navController = rememberNavController(), productosModel = productos, ticketModel = TicketViewModel())
    }
}