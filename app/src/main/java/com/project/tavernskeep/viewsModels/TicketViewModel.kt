package com.project.tavernskeep.viewsModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tavernskeep.models.LineaTicketModel
import com.project.tavernskeep.models.TicketModel
import com.project.tavernskeep.services.ApiServices
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TicketViewModel : ViewModel() {
    var ticketModelListResponse: List<TicketModel> by mutableStateOf(listOf())
    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate: String = sdf.format(Date())
    //val aux1: LineaTicketModel = LineaTicketModel("asdasd", 12, 64.00, 1231.00)
    //val aux2: LineaTicketModel = LineaTicketModel("qweqed", 12, 64.00, 1231.00)
    var lineaTicketList: MutableList<LineaTicketModel> by mutableStateOf(mutableListOf())
    var ticketModel: TicketModel by mutableStateOf(TicketModel(
            "T1241241251",
            "T1241241251",
            "mesa1",
            lineaTicketList,
            currentDate,
        )
    )
    private var errorMessage: String by mutableStateOf("")



    fun getTicketList() {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val ticketList = apiServices.getTicketList()
                ticketModelListResponse = ticketList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1Ticket(codigo: String) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val ticket1 = apiServices.getTicket(codigo)
                if(ticket1.isSuccessful){
                    ticketModel = ticket1.body()!!
                    lineaTicketList = ticketModel.listaproductos.toMutableList()
                }else{
                    Log.d("EROOR", "Error cargando los tickets.")
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
                Log.d("ERROR CONENCTANDO", e.message.toString())
            }
        }
    }

    fun actualizeTicket(t: TicketModel) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val ticket1 = apiServices.actualize1Ticket(ticket = t, codigo = t.codigo)
                if(ticket1.isSuccessful){
                    ticketModel = ticket1.body()!!
                }else{
                    Log.d("EROOR", "Error cargando los tickets.")
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
                Log.d("ERROR CONENCTANDO", e.message.toString())
            }
        }
    }

    fun uploadTicket(t: TicketModel) {
        viewModelScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val ticket1 = apiServices.upload1Ticket(t)
                if(ticket1.isSuccessful){
                    ticketModel = ticket1.body()!!
                }else{
                    Log.d("EROOR", "Error cargando los tickets.")
                }
            }
            catch (e : Exception) {
                errorMessage = e.message.toString()
                Log.d("ERROR CONENCTANDO", e.message.toString())
            }
        }
    }
}