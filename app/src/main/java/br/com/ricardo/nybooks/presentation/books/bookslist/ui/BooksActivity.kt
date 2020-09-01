package br.com.ricardo.nybooks.presentation.books.bookslist.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.base.BaseActivity
import br.com.ricardo.nybooks.base.ViewModelFactory
import br.com.ricardo.nybooks.presentation.books.booksdetail.ui.BooksDetailActivity
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepository
import br.com.ricardo.nybooks.presentation.books.bookslist.repository.BookRepositoryImpl
import br.com.ricardo.nybooks.presentation.books.bookslist.ui.adappter.BooksAdapter
import br.com.ricardo.nybooks.presentation.books.bookslist.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    private lateinit var mViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(books_toolbar, R.string.books_title)

        mViewModel = ViewModelFactory(BookRepositoryImpl()).create(BooksViewModel::class.java)

        mViewModel.mBooksMutableLiveData.observe(this, Observer {
            it?.let { books ->
                with(books_recycler) {
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BooksDetailActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        mViewModel.mViewFlipperMutableLiveData.observe(this, Observer {
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