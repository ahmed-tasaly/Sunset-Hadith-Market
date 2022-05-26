package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.books.Books;
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse;
import com.moataz.afternoonhadeeth.databinding.ListBooksBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.DisplayBookActivity;

import java.util.Collections;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BooksResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setBooksList(BooksResponse items) {
        this.items = items;
        notifyDataSetChanged();
        Collections.shuffle(items.getBooksList());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BooksViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_books,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Books books = items.getBooksList().get(position);
        ((BooksViewHolder) holder).listBooksBinding.setBooksModel(books);
        ((BooksViewHolder) holder).setOnClick(books);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getBooksList().size();
    }

    static class BooksViewHolder extends RecyclerView.ViewHolder {
        ListBooksBinding listBooksBinding;

        BooksViewHolder(@NonNull ListBooksBinding itemView) {
            super(itemView.getRoot());
            listBooksBinding = itemView;
            listBooksBinding.ratingBar.setRating((float) 5);
        }

        void setOnClick(Books books) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DisplayBookActivity.class);
                // send data to DisplayBookActivity
                intent.putExtra("bookTitle", books.getTitle());
                intent.putExtra("bookAuthor", books.getAuthor());
                intent.putExtra("bookDetails", books.getDetails());
                intent.putExtra("bookImage", books.getImageURL());
                intent.putExtra("bookUrl", books.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}