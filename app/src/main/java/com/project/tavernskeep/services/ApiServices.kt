package com.project.tavernskeep.services

import com.project.tavernskeep.models.EmployeeModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {

    @GET("empleado")
    suspend fun getEmployee() : List<EmployeeModel>

    companion object {
        private var apiServices:ApiServices? = null

        fun getInstance() : ApiServices {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("http://25.81.98.245:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }

            return apiServices!!
        }
    }
}