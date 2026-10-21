package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Danh sách lưu trong bộ nhớ, dùng chung cho các Activity
    public static final ArrayList<Book> books = new ArrayList<>();

    private BookAdapter adapter;
    private TextView tvTotal, tvRead, tvUnread;

    private final ActivityResultLauncher<Intent> addLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Book book = (Book) result.getData().getSerializableExtra("book");
                    if (book != null) {
                        books.add(book);
                        refresh();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (books.isEmpty()) {
            books.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", Book.DANG_DOC, 4, ""));
            books.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", Book.DA_DOC, 5, ""));
            books.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", Book.CHUA_DOC, 0, ""));
        }

        tvTotal = findViewById(R.id.tvTotal);
        tvRead = findViewById(R.id.tvRead);
        tvUnread = findViewById(R.id.tvUnread);
        ListView lvBooks = findViewById(R.id.lvBooks);
        Button btnAdd = findViewById(R.id.btnAdd);

        adapter = new BookAdapter(this, books);
        lvBooks.setAdapter(adapter);

        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(v -> addLauncher.launch(new Intent(this, AddBookActivity.class)));

        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        adapter.notifyDataSetChanged();
        int read = 0;
        for (Book b : books) {
            if (b.getStatus().equals(Book.DA_DOC)) read++;
        }
        tvTotal.setText(String.valueOf(books.size()));
        tvRead.setText(String.valueOf(read));
        tvUnread.setText(String.valueOf(books.size() - read));
    }
}