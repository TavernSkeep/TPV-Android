package com.project.tavernskeep.screens.home.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.compose.base.R
import coil.transform.CircleCropTransformation
import com.project.tavernskeep.models.EmployeeModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.ui.theme.TavernSkeepTheme

@Composable
fun EmployeeItem(navController: NavController, employeeModel: EmployeeModel, id: Int) {
    Card(modifier = Modifier.padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp),/*
        .clickable {
            navController.navigate(route = "${Screen.Detail.route}/${id}")
        },*/
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
                .fillMaxSize(),
        ) {/*
            Image(
                painter = rememberImagePainter(
                    data = employeeModel.imageUrl,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.notification_action_background)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = employeeModel.description,
                modifier =  Modifier.fillMaxHeight()
                    .weight(0.2f),
            )*/
            Column(
                modifier = Modifier.padding(4.dp)
                    .fillMaxHeight()
                    .weight(0.8f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = employeeModel.dni,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = employeeModel.nombre,
                    style = MaterialTheme.typography.caption,
                    color = Color.Red,
                    modifier = Modifier.background(
                        Color.White
                    )
                        .padding(4.dp),
                )
                Text(
                    text = employeeModel.apellidos,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Green,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TavernSkeepTheme {
        val employee = EmployeeModel("a", "444222111A", "1234", "DJ", "Sapliqui", "66655666", "Chad", "sapliqui@gmail.com")
        EmployeeItem(navController = rememberNavController(), employeeModel = employee, id = 0)
    }
}