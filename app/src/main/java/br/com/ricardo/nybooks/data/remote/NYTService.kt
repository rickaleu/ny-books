package br.com.ricardo.nybooks.data.remote

import br.com.ricardo.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTService {

    @GET("lists.json")
    fun listBooks(
        @Query("api-key") apiKey: String = "1AjkQ0ZYeNIfekbRLu4asTSacZTACble",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}