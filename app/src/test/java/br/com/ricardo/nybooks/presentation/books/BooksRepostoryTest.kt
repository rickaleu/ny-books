package br.com.ricardo.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.ricardo.nybooks.data.remote.NYTService
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(MockitoJUnitRunner::class)
class BooksRepostoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: BookRepository

    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var service: NYTService

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.nytimes.com/svc/books/v3/lists.json")
            .build()
            .create(NYTService::class.java)
    }


    @Test
    fun `fetch getBook success`() {
        //Arrenge


        //Act


        //Assert
    }
}