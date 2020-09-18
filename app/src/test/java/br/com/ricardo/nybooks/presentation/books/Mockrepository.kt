package br.com.ricardo.nybooks.presentation.books

import br.com.ricardo.nybooks.data.BooksResult
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository

class MockRepository(private val result: BooksResult) : BookRepository {
    override fun getBookList(booksResultCallBack: (result: BooksResult) -> Unit) {
        booksResultCallBack(result)
    }

}