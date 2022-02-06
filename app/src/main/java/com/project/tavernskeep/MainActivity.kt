package com.project.tavernskeep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.tavernskeep.screens.Screen
import com.project.tavernskeep.screens.home.HomeScreen
import com.project.tavernskeep.ui.theme.TavernSkeepTheme
import com.project.tavernskeep.viewsModels.EmployeeViewModel

class MainActivity : ComponentActivity() {

    private val employeeViewModel by viewModels<EmployeeViewModel>()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TavernSkeepTheme {
                employeeViewModel.getEmployeeList()
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(
                        route = Screen.Home.route
                    ) {
                        HomeScreen(
                            navController = navController,
                            employeeModelList = employeeViewModel.employeeModelListResponse,
                        )
                    }
                    composable(
                        route = Screen.Detail.route+Screen.Detail.arguments,
                        arguments = listOf(
                            navArgument("id") {type = NavType.IntType}
                        ),
                    ) {

                            backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        requireNotNull(id)
                        /*
                        DetailScreen(
                            navController = navController,
                            movieModel = movieViewModel.movieModelListResponse[id]
                        )*/

                    }
                }
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/
            }
        }
    }
}
/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TavernSkeepTheme {
        Greeting("Android")
    }
}*/