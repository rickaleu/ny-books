package br.com.ricardo.nybooks.data.response

import br.com.ricardo.nybooks.data.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailsResponse (
    @Json(name = "title")
    val titleResponse: String,
    @Json(name = "author")
    val authorResponse: String,
    @Json(name = "description")
    val descriptionResponse: String
) {
    fun getBookModel() = Book(
        title = this.titleResponse,
        author = this.authorResponse,
        description = this.descriptionResponse)
}