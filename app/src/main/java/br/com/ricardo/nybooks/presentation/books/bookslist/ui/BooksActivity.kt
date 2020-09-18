package br.com.ricardo.nybooks.presentation.books.bookslist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.base.BaseActivity
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.di.ViewModelFactory
import br.com.ricardo.nybooks.presentation.books.booksdetail.ui.BooksDetailActivity
import br.com.ricardo.nybooks.presentation.books.booksdetail.viewmodel.BooksDetailViewModel
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepositoryImpl
import br.com.ricardo.nybooks.presentation.books.bookslist.ui.adappter.BooksAdapter
import br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    private val mViewModel: BooksViewModel by lazy {
        ViewModelFactory(BookRepositoryImpl()).create(BooksViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(books_toolbar, R.string.books_title)

        mViewModel.booksMutableLiveData.observe(this, Observer {
            it?.let { books ->
                with(books_recycler) {
                    layoutManager = LinearLayoutManager(this@BooksActivity,
                        LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BooksDetailActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        mViewModel.viewFlipperMutableLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                books_view_flipper.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessage ->
                    books_text_error.text = getString(errorMessage)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getBooks()
    }
}