package br.com.ricardo.nybooks.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository
import br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel.BooksViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class ")
    }

}