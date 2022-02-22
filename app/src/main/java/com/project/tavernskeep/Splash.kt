package com.project.tavernskeep

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.project.tavernskeep.viewsModels.EmployeeViewModel
import kotlin.system.exitProcess
import com.project.tavernskeep.screens.Screen.Home

class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val task = Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContent {
            TavernSkeepTheme {
                CircularProgressIndicatorSample()
            }
        }
        val handler = Handler()
        handler.postDelayed(task, 3000)
    }
    @Override
    override fun onBackPressed() {
    }
}

@Composable
fun CircularProgressIndicatorSample() {
    Column(modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        CircularProgressIndicator()
    }
}