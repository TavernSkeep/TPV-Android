package com.project.tavernskeep.services

import com.project.tavernskeep.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.FormUrlEncoded

import retrofit2.http.PUT




interface ApiServices {

    @GET("empleado")
    suspend fun getEmployees() : List<EmployeeModel>

    @GET("empleado/{dni}")
    suspend fun getEmployee(@Path("dni")dni: String) : Response<EmployeeModel>

    @GET("mesa")
    suspend fun getMesas() : List<MesasModel>

    @GET("mesa/{codigo}")
    suspend fun getMesa(@Path("codigo")codigo: String) : Response<MesasModel>

    @PUT("mesa/{codigo}")
    suspend fun actualize1Mesa(
        @Path("codigo")codigo: String,
        @Body mesa : MesasModel
    ) : Response<MesasModel>
    /*
    @PUT("mesa/{codigo}")
    suspend fun actualize1Mesa(
        @Body extra : MesasModel
    ) : MesasModel*/

    @GET("producto")
    suspend fun getProductos() : Response<List<ProductosModel>>

    @GET("producto/{codigo}")
    suspend fun getProducto(@Path("codigo")codigo: String) : Response<ProductosModel>

    @GET("ticket")
    suspend fun getTicketList() : List<TicketModel>

    @GET("ticket/{codigo}")
    suspend fun getTicket(@Path("codigo")codigo: String) : Response<TicketModel>

    @POST("ticket")
    suspend fun upload1Ticket(
        @Body extra : TicketModel
    ) : Response<TicketModel>

    @PUT("ticket/{codigo}")
    suspend fun actualize1Ticket(
        @Path("codigo")codigo: String,
        @Body ticket : TicketModel
    ) : Response<TicketModel>


    companion object {
        private var apiServices:ApiServices? = null

        fun getInstance() : ApiServices {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("http://172.20.10.5:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }

            return apiServices!!
        }
    }
}