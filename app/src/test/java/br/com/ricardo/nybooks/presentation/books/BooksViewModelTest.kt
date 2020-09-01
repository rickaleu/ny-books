package br.com.ricardo.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.data.BooksResult
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: BooksViewModel

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    @Test
    fun `fetch getBooks with liveData success`() {
        //Arrange
        val books = listOf(
            Book("title 1", "ricardo", "description 1")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))

        viewModel = BooksViewModel(resultSuccess)
        viewModel.mBooksMutableLiveData.observeForever(booksLiveDataObserver)
        viewModel.mViewFlipperMutableLiveData.observeForever(viewFlipperLiveDataObserver)

        //Atc
        viewModel.getBooks()

        //Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `fetch getBooks with liveData empty`() {
        val resultSuccess = MockRepository(BooksResult.Success(listOf()))

        viewModel = BooksViewModel((resultSuccess))
        viewModel.mBooksMutableLiveData.observeForever(booksLiveDataObserver)
        viewModel.mViewFlipperMutableLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(booksLiveDataObserver).onChanged(listOf())
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `fetch getBook with liveData api error`() {
        val statusCode = 401
        val message = R.string.books_view_flipper_error_401
        val resultApiError = MockRepository(BooksResult.ApiError(statusCode))

        viewModel = BooksViewModel(resultApiError)
        viewModel.mViewFlipperMutableLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, message))
    }

    @Test
    fun `fetch getBook with liveData api error generic`() {
        val statusCode = 499
        val message = R.string.books_view_flipper_error_400_generic
        val resultApiError = MockRepository(BooksResult.ApiError(statusCode))

        viewModel = BooksViewModel(resultApiError)
        viewModel.mViewFlipperMutableLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, message))
    }

    @Test
    fun `fetch getBook with liveData server error`() {
        val message = R.string.books_view_flipper_error_500
        val resultServerError = MockRepository(BooksResult.ServerError)

        viewModel = BooksViewModel(resultServerError)
        viewModel.mViewFlipperMutableLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModel.getBooks()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, message))
    }

}