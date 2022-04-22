package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.books.Books;
import com.moataz.afternoonhadeeth.data.model.books.BooksResponse;
import com.moataz.afternoonhadeeth.databinding.ListBooksBinding;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

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
            itemView.setOnClickListener(v -> Intents.INSTANCE.openTabUrl(itemView.getContext(), books.getUrl()));
        }
    }
}