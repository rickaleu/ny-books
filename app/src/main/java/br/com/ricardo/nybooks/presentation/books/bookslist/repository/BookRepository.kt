package br.com.ricardo.nybooks.presentation.books.bookslist.repository

import br.com.ricardo.nybooks.data.BooksResult

interface BookRepository {

    fun getBookList(booksResultCallBack: (result: BooksResult) -> Unit)

}