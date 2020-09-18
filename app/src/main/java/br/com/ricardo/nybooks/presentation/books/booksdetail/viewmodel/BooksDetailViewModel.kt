package br.com.ricardo.nybooks.presentation.books.booksdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BooksDetailViewModel: ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description


    fun getDetails(t: String, d: String) {
        _title.value = t
        _description.value = d
    }

}