package br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.data.BooksResult
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepositoryImpl
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository

class BooksViewModel(private val repository: BookRepository) : ViewModel() {

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    val mBooksMutableLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val mViewFlipperMutableLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()


    fun getBooks() {
        repository.getBookList { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    mBooksMutableLiveData.value = result.books
                    mViewFlipperMutableLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        mViewFlipperMutableLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_401)
                    } else {
                        mViewFlipperMutableLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    mViewFlipperMutableLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_500)
                }
            }
        }
    }


}