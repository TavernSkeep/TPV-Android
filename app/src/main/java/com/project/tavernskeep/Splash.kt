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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import android.widget.Toast




class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val task = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
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
}

@Composable
fun CircularProgressIndicatorSample() {
    Column(modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        CircularProgressIndicator()
    }
}