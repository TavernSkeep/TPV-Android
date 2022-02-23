package com.project.tavernskeep.screens.mesas.views


import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.google.android.material.color.MaterialColors
import com.project.tavernskeep.R
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.viewsModels.MesasViewModel

@Composable
fun MesasItem(navController: NavController, mesasModel: MesasModel, mesas: MesasViewModel) {
    var auxColor: Color
    var auxImg: Int
    if(mesasModel.is_reservada){
        auxColor = colorResource(id = R.color.orange)
        auxImg = R.drawable.sword_reservado
    }else if(mesasModel.ticket_actual == "uwu"){
        auxColor = colorResource(id = R.color.green)
        auxImg = R.drawable.sword_libre
    }else{
        auxColor = colorResource(id = R.color.red)
        auxImg = R.drawable.sword_ocupado
    }/*
    var auxRoute: String = Screen.Pedidos.route
    if(mesasModel.ticket_actual != "")
     auxRoute = "${Screen.Pedidos.route}/${mesasModel.ticket_actual}"*/

    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp)
        .clickable {
            mesas.mesasModel._id = mesasModel._id
            mesas.mesasModel.codigo = mesasModel.codigo
            mesas.mesasModel.ticket_actual = mesasModel.ticket_actual
            mesas.mesasModel.is_reservada = mesasModel.is_reservada
            mesas.mesasModel.n_sillas = mesasModel.n_sillas
            mesas.mesasModel.zona = mesasModel.zona

            mesas.editandoMesas = mesasModel.ticket_actual == "uwu"

            navController.navigate(
                route = "${Screen.Pedidos.route}/${mesasModel.ticket_actual}") },

        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
                .background(auxColor),
        ) {
            Image(
                painter = painterResource(id = auxImg),
                //contentScale = ContentScale.Crop,
                    contentDescription = "Reservada: " + mesasModel.is_reservada,
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
                    text = mesasModel.codigo,
                    style = TextStyle(fontFamily = Arcade),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = mesasModel.zona,
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
                    text = "Nº Sillas: " + mesasModel.n_sillas,
                    style = TextStyle(fontFamily = Arcade),/*
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,*/
                    color = Color.Yellow,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TavernSkeepTheme {
        val mesas = MesasModel("a", "mesa1", "Comedor principal", 1, false, "")
        MesasItem(navController = rememberNavController(), mesasModel = mesas, mesas = MesasViewModel())
    }
}