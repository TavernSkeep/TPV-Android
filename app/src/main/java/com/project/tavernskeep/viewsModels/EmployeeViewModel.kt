package com.project.tavernskeep.viewsModels

import android.os.Debug
import android.util.Log
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
    private var errorMessage : String by mutableStateOf("")

    fun getEmployeeList() {
        viewModelScope.launch{
            val apiServices = ApiServices.getInstance()
            try {
                //Log.d("empleado", "sdafafasdfsadsa")
                val employeeList = apiServices.getEmployee()
                employeeModelListResponse = employeeList
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}