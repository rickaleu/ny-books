package br.com.ricardo.nybooks.presentation.books.bookslist.repository

import br.com.ricardo.nybooks.data.BooksResult
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.data.remote.RetrofitClient
import br.com.ricardo.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepositoryImpl : BookRepository {

    private val mService = RetrofitClient.SERVICE

    override fun getBookList(booksResultCallBack: (result: BooksResult) -> Unit) {
        mService.listBooks().enqueue(object : Callback<BookBodyResponse> {

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallBack.invoke(BooksResult.ServerError)
            }

            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let {
                        for (result in it.bookResultsResponse) {
                            val book = result.bookDetailsResponse[0].getBookModel()
                            books.add(book)
                        }
                    }

                    booksResultCallBack.invoke(BooksResult.Success(books))
                } else {
                    booksResultCallBack.invoke(BooksResult.ApiError(response.code()))
                }
            }
        })
    }
}