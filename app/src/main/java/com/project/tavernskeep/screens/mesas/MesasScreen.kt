package com.project.tavernskeep.screens.mesas

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.project.tavernskeep.MainActivity
import com.project.tavernskeep.models.EmployeeModel
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.screens.mesas.views.MesasList
import com.project.tavernskeep.ui.theme.Arcade
import com.project.tavernskeep.viewsModels.EmployeeViewModel
import com.project.tavernskeep.viewsModels.MesasViewModel
import kotlin.system.exitProcess

@Composable
fun MesasScreen(navController: NavController, mesasModel: MesasViewModel, context: Configuration, employeeModel: EmployeeViewModel, context2: Context) {
    Scaffold(topBar ={
        TopAppBar() {
            Icon(imageVector = Icons.Default.ExitToApp,
                contentDescription = "Volver a categorías",tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .clickable { employeeModel.logged = false
                        val intent = Intent(context2, MainActivity::class.java)
                        startActivity(context2, intent, Bundle())}
                    .width(50.dp)
                    .height(50.dp))
            if(context.orientation == Configuration.ORIENTATION_PORTRAIT)
                Spacer(modifier = Modifier.fillMaxWidth(0.24f))
            else
                Spacer(modifier = Modifier.fillMaxWidth(0.37f))
            Text(text = "Mesas", style = TextStyle(fontFamily = Arcade), color = MaterialTheme.colors.onBackground, fontSize = 25.sp)
        }
    }) {
        //val aux: List<MesasModel> = mesasModel.mesasModelListResponse.sortedBy {M -> M.codigo}.toList()
        bodyContent(navController, mesasModel)
    }
}

@Composable
fun bodyContent(navController: NavController, mesasModel: MesasViewModel){
    Surface(color = MaterialTheme.colors.background) {
        MesasList(navController = navController, mesasModel = mesasModel)
    }
}