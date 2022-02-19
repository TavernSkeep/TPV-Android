package com.project.tavernskeep.services

import com.project.tavernskeep.models.EmployeeModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("empleado")
    suspend fun getEmployees() : List<EmployeeModel>

    @GET("empleado/{dni}")
    suspend fun getEmployee(@Path("dni")dni: String) : Response<EmployeeModel>

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