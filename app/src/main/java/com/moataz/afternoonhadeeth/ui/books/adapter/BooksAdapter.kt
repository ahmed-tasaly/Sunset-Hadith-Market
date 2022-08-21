package com.moataz.afternoonhadeeth.ui.books.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.books.Books
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse
import com.moataz.afternoonhadeeth.databinding.ListBooksBinding
import com.moataz.afternoonhadeeth.ui.books.view.activity.DisplayBookActivity

class BooksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: BooksResponse? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setBooksList(items: BooksResponse) {
        this.items = items
        notifyDataSetChanged()
        items.booksList.shuffled()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BooksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_books,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val books = items!!.booksList[position]
        (holder as BooksViewHolder).listBooksBinding.booksModel = books
        holder.setOnClick(books)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.booksList.size
    }

    internal class BooksViewHolder(var listBooksBinding: ListBooksBinding) :
        RecyclerView.ViewHolder(
            listBooksBinding.root
        ) {
        fun setOnClick(books: Books) {
            itemView.setOnClickListener { v: View? ->
                val intent = Intent(itemView.context, DisplayBookActivity::class.java)
                // send data to DisplayBookActivity
                intent.putExtra("bookTitle", books.title)
                intent.putExtra("bookAuthor", books.author)
                intent.putExtra("bookDetails", books.details)
                intent.putExtra("bookImage", books.imageURL)
                intent.putExtra("bookUrl", books.url)
                itemView.context.startActivity(intent)
            }
        }

        init {
            listBooksBinding.ratingBar.rating = 5f
        }
    }
}