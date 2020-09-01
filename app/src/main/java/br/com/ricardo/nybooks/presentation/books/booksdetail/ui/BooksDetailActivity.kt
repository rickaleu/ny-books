package br.com.ricardo.nybooks.presentation.books.booksdetail.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.base.BaseActivity
import br.com.ricardo.nybooks.databinding.ActivityBookDetailBinding
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

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(books_toolbar, R.string.book_details_title, true)
//        books_toolbar.title = getString(R.string.book_details_title)
//        setSupportActionBar(books_toolbar)


        binding.booksDetailTitle.text = intent.getStringExtra(EXTRA_TITLE)
        binding.booksDetailDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }
}