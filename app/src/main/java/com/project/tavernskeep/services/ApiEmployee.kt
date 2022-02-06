package com.project.tavernskeep.services

import com.project.tavernskeep.models.EmployeeModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiEmployee {

    @GET("employees.json")
    suspend fun getEmployee() : List<EmployeeModel>

    companion object {
        private var apiServices:ApiEmployee? = null

        fun getInstance() : ApiEmployee {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("http://25.81.98.245:8080/empleado/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiEmployee::class.java)
            }

            return apiServices!!
        }
    }
}