package br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.base.NyConstants
import br.com.ricardo.nybooks.data.BooksResult
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepositoryImpl
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository

class BooksViewModel(private val repository: BookRepository) : ViewModel() {

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    private val _booksMutableLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val booksMutableLiveData: LiveData<List<Book>>
    get() = _booksMutableLiveData

    private val _viewFlipperMutableLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()
    val viewFlipperMutableLiveData: LiveData<Pair<Int, Int?>>
    get() = _viewFlipperMutableLiveData

    fun getBooks() {
        repository.getBookList { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    _booksMutableLiveData.value = result.books
                    _viewFlipperMutableLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == NyConstants.HTTP_ERROR_UNAUTHORIZED) {
                        _viewFlipperMutableLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_401)
                    } else {
                        _viewFlipperMutableLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    _viewFlipperMutableLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.books_view_flipper_error_500)
                }
            }
        }
    }


}