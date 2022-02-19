package com.project.tavernskeep.viewsModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.project.tavernskeep.models.EmployeeModel
import com.project.tavernskeep.services.ApiServices
import java.lang.Exception

class EmployeeViewModel : ViewModel(){
    var employeeModelListResponse : List<EmployeeModel> by mutableStateOf(listOf())
    var employeeModel : EmployeeModel by mutableStateOf(EmployeeModel("uwu", "uwu", "uwu", "uwu", "uwu", "uwu", "uwu", "uwu"))
    private var errorMessage : String by mutableStateOf("")

    fun getEmployeeList() {
        viewModelScope.launch{
            val apiServices = ApiServices.getInstance()
            try {
                val employeeList = apiServices.getEmployees()
                employeeModelListResponse = employeeList
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1Employee(dni: String, pwd: String, context: Context) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val employee1 = apiServices.getEmployee(dni)
                if(employee1.isSuccessful){
                    employeeModel = employee1.body()!!
                    checkLogin(employeeModel, dni, pwd, context)
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    private fun checkLogin(employee: EmployeeModel, dni: String, pwd: String, context: Context) {
        if(employee.dni == dni && employee.contraseña == pwd){
            Toast.makeText(context, "EMPEZAMOS CON LOS HIJOS DE P", Toast.LENGTH_LONG).show()
        }
    }
}