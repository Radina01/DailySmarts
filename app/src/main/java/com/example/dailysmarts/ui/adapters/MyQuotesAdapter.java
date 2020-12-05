package com.example.dailysmarts.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailysmarts.R;
import com.example.dailysmarts.data.database.Quote;
import com.example.dailysmarts.data.database.QuoteDBService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyQuotesAdapter extends RecyclerView.Adapter<MyQuotesAdapter.ViewHolder> {

    List<Quote> quotes;
    Context context;

    private QuoteDBService quoteDBService;

    public MyQuotesAdapter(Context context) {
        quotes = new ArrayList<>();
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quote, parent, false);
        quoteDBService = new QuoteDBService(context);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.txtAuthor.setText(quote.quoteAuthor);
        holder.txtQuote.setText(quote.quoteText);
        holder.btnSave.setOnClickListener(v-> deleteFromDb(quote));
        holder.btnShare.setOnClickListener(v -> shareQuote(quote));
    }

    private void shareQuote(Quote quote) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = quote.getQuoteText() + "\n         -" + quote.getQuoteAuthor();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    private void deleteFromDb(Quote quote){
        quoteDBService.deleteQuote(quote);
        quotes.remove(quote);
        notifyDataSetChanged();
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
