package br.com.ricardo.nybooks.presentation.books.booksdetail.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.base.BaseActivity
import br.com.ricardo.nybooks.databinding.ActivityBookDetailBinding
import br.com.ricardo.nybooks.presentation.books.booksdetail.viewmodel.BooksDetailViewModel
import kotlinx.android.synthetic.main.activity_book_detail.*
//import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_DESCRIPTION = "description"

        fun getStartIntent(context: Context, title: String, description: String) : Intent {
            return Intent(context, BooksDetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }

    private val booksViewModel: BooksDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityBookDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

//        setupToolbar(books_toolbar, R.string.book_details_title, true)

        binding.viewmodel = booksViewModel
        binding.lifecycleOwner = this


//        binding.booksDetailTitle.text = intent.getStringExtra(EXTRA_TITLE)
//        binding.booksDetailDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }

    override fun onResume() {
        super.onResume()
        booksViewModel.getDetails(
            intent.getStringExtra(EXTRA_TITLE),
            intent.getStringExtra(EXTRA_DESCRIPTION)
        )
    }
}