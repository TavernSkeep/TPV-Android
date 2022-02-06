package com.project.tavernskeep.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.tavernskeep.models.EmployeeModel
import com.project.tavernskeep.screens.home.views.EmployeeList

@Composable
fun HomeScreen(navController: NavController, employeeModelList : List<EmployeeModel>) {
    Surface(color = MaterialTheme.colors.background) {
        EmployeeList(navController = navController, employeeModelList = employeeModelList)
    }
}