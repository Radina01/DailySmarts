package com.example.dailysmarts.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailysmarts.R;
import com.example.dailysmarts.data.database.Quote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyQuotesAdapter extends RecyclerView.Adapter<MyQuotesAdapter.ViewHolder> {

    List<Quote> quotes;

    @Inject
    public MyQuotesAdapter() {
        quotes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quote, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.txtAuthor.setText(quote.quoteAuthor);
        holder.txtQuote.setText(quote.quoteText);

    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuote;
        TextView txtAuthor;
        Button btnSave;
        Button btnShare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuote = itemView.findViewById(R.id.txtQuote);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            btnSave = itemView.findViewById(R.id.btnSave);
            btnShare = itemView.findViewById(R.id.btnShare);
        }
    }
}
