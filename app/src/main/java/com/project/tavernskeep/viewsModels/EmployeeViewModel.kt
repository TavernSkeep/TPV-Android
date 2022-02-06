package com.project.tavernskeep.viewsModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.project.tavernskeep.models.EmployeeModel
import com.project.tavernskeep.services.ApiEmployee
import java.lang.Exception

class EmployeeViewModel : ViewModel(){
    var employeeModelListResponse : List<EmployeeModel> by mutableStateOf(listOf())
    private var errorMessage : String by mutableStateOf("")

    fun getEmployeeList() {
        viewModelScope.launch{
            val apiServices = ApiEmployee.getInstance()
            try {
                val employeeList = apiServices.getEmployee()
                employeeModelListResponse = employeeList
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}