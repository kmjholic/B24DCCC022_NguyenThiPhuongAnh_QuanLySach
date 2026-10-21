package com.example.quanlysach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }
        Book b = getItem(position);
        ((TextView) convertView.findViewById(R.id.tvTitle)).setText(b.getTitle());
        ((TextView) convertView.findViewById(R.id.tvAuthor)).setText("Tác giả: " + b.getAuthor());
        ((TextView) convertView.findViewById(R.id.tvCategory)).setText(b.getCategory());

        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        tvStatus.setText(b.getStatus());
        tvStatus.setBackgroundResource(b.getStatusBg());
        tvStatus.setTextColor(Color.parseColor(b.getStatusColor()));

        ((TextView) convertView.findViewById(R.id.tvStars)).setText(b.getStars());
        return convertView;
    }
}