package br.com.ricardo.nybooks.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private lateinit var retrofit : Retrofit

     private fun initRetrofit() : Retrofit {
         val client = OkHttpClient.Builder()

         retrofit = Retrofit.Builder()
             .addConverterFactory(MoshiConverterFactory.create())
             .baseUrl("https://api.nytimes.com/svc/books/v3/")
             .client(client.build())
             .build()

         return retrofit
     }

    val service: NYTService = initRetrofit().create(NYTService::class.java)
}