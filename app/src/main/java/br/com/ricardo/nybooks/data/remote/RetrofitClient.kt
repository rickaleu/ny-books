package br.com.ricardo.nybooks.data.remote

import br.com.ricardo.nybooks.base.NyConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private lateinit var retrofit : Retrofit

     private fun initRetrofit() : Retrofit {
         val client = OkHttpClient.Builder()

         retrofit = Retrofit.Builder()
             .addConverterFactory(MoshiConverterFactory.create())
             .baseUrl(NyConstants.NY_URL)
             .client(client.build())
             .build()

         return retrofit
     }

    val SERVICE: NYService = initRetrofit().create(NYService::class.java)
}