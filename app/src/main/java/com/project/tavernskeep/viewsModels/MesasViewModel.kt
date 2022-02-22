package com.project.tavernskeep.viewsModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tavernskeep.models.MesasModel
import com.project.tavernskeep.services.ApiServices
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MesasViewModel : ViewModel(){
    var mesasModelListResponse : List<MesasModel> by mutableStateOf(listOf())
    var mesasModel: MesasModel by mutableStateOf(MesasModel("uwu", "uwu", "uwu", 1, false, "uwu"))
    //var editMesa: MesasModel by mutableStateOf(MesasModel("", "", "", 0, false, ""))
    var editandoMesas by mutableStateOf(false)
    private var errorMessage : String by mutableStateOf("")

    fun getMesasList() {
        viewModelScope.launch{
            val apiServices = ApiServices.getInstance()
            try {
                val mesasList = apiServices.getMesas()
                mesasModelListResponse = mesasList
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun actualizeMesa(m: MesasModel) {
        viewModelScope.launch{
            val apiServices = ApiServices.getInstance()
            try {
                val mesa = apiServices.actualize1Mesa(mesa = m, codigo = m.codigo)
                //mesasModel = mesa
                if(mesa.isSuccessful){
                    mesasModel = mesa.body()!!
                }else{
                    Log.d("EROOR", "Error cargando las mesas.")
                }

            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}