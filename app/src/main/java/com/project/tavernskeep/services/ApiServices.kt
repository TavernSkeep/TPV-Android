package com.project.tavernskeep.services

import com.project.tavernskeep.models.ProductModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {

    @GET("movies.json")
    suspend fun getMovies() : List<ProductModel>

    companion object {
        private var apiServices:ApiServices? = null

        fun getInstance() : ApiServices {
            if(apiServices == null) {
                apiServices = Retrofit.Builder()
                    .baseUrl("https://dam-2122-default-rtdb.europe-west1.firebasedatabase.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }

            return apiServices!!
        }
    }
}