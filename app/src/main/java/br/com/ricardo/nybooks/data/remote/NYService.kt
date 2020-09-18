package br.com.ricardo.nybooks.data.remote

import br.com.ricardo.nybooks.base.NyConstants
import br.com.ricardo.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYService {

    @GET("lists.json")
    fun listBooks(
        @Query("api-key") apiKey: String = NyConstants.NY_API_KEY,
        @Query("list") list: String = NyConstants.NY_DEFAULT_LIST
    ): Call<BookBodyResponse>
}