package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        TextView tvBack = findViewById(R.id.tvBack);
        EditText edtTitle = findViewById(R.id.edtTitle);
        EditText edtAuthor = findViewById(R.id.edtAuthor);
        Spinner spCategory = findViewById(R.id.spCategory);
        RadioGroup rgStatus = findViewById(R.id.rgStatus);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvRating = findViewById(R.id.tvRating);
        EditText edtNote = findViewById(R.id.edtNote);
        Button btnSave = findViewById(R.id.btnSave);

        tvBack.setOnClickListener(v -> finish());

        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        spCategory.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories));

        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) ->
                tvRating.setText(((int) rating) + " / 5 sao"));

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String author = edtAuthor.getText().toString().trim();

            if (title.isEmpty()) {
                edtTitle.setError("Không được để trống");
                return;
            }
            if (author.isEmpty()) {
                edtAuthor.setError("Không được để trống");
                return;
            }

            RadioButton rb = findViewById(rgStatus.getCheckedRadioButtonId());
            Book book = new Book(title, author, spCategory.getSelectedItem().toString(),
                    rb.getText().toString(), ratingBar.getRating(), edtNote.getText().toString().trim());

            Intent data = new Intent();
            data.putExtra("book", book);
            setResult(RESULT_OK, data);
            finish();
        });
    }
}