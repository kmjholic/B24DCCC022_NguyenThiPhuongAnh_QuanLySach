package com.example.quanlysach;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    private Book book;
    private TextView tvStatus;
    private Button btnMarkRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        int position = getIntent().getIntExtra("position", -1);
        if (position < 0 || position >= MainActivity.books.size()) {
            finish();
            return;
        }
        book = MainActivity.books.get(position);

        TextView tvBackArrow = findViewById(R.id.tvBackArrow);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthorCategory = findViewById(R.id.tvAuthorCategory);
        tvStatus = findViewById(R.id.tvStatus);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvNote = findViewById(R.id.tvNote);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        tvTitle.setText(book.getTitle());
        tvAuthorCategory.setText(book.getAuthor() + "  ·  " + book.getCategory());
        ratingBar.setRating(book.getRating());
        tvNote.setText(book.getNote().isEmpty() ? "(Không có ghi chú)" : book.getNote());
        updateStatus();

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> book.setRating(rating));

        btnMarkRead.setOnClickListener(v -> {
            book.setStatus(Book.DA_DOC);
            updateStatus();
        });

        tvBackArrow.setOnClickListener(v -> finish());
        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            MainActivity.books.remove(position);
            finish();
        });
    }

    private void updateStatus() {
        tvStatus.setText(book.getStatus());
        tvStatus.setBackgroundResource(book.getStatusBg());
        tvStatus.setTextColor(Color.parseColor(book.getStatusColor()));
        btnMarkRead.setEnabled(!book.getStatus().equals(Book.DA_DOC));
    }
}