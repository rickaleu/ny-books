package br.com.ricardo.nybooks.presentation.books.bookslist.ui.adappter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.nybooks.R
import br.com.ricardo.nybooks.data.model.Book
import br.com.ricardo.nybooks.databinding.ItemBookBinding
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private val listBooks: List<Book>,
                   private val onClickListener: ((Book: Book) -> Unit))
    : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private lateinit var binding: ItemBookBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = listBooks.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(listBooks[position])
    }

    class BooksViewHolder(binding: ItemBookBinding, private val onClickListener: ((Book: Book) -> Unit))
        : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.itemTextTitle
        private val author = binding.itemTextAutor

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onClickListener.invoke(book)
            }
        }

    }
}