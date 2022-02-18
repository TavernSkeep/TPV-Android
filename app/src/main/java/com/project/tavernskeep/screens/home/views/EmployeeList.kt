package com.project.tavernskeep.screens.home.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.tavernskeep.models.EmployeeModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

@Composable
fun EmployeeList (navController: NavController, employeeModelList: List<EmployeeModel>) {
    LazyColumn {
        itemsIndexed(items = employeeModelList) {
                index, item -> EmployeeItem(navController = navController, employeeModel = item, id = index)
        }
    }
}