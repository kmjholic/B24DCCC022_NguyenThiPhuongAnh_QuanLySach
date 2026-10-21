package com.example.quanlysach;

import java.io.Serializable;

public class Book implements Serializable {
    public static final String CHUA_DOC = "Chưa đọc";
    public static final String DANG_DOC = "Đang đọc";
    public static final String DA_DOC = "Đã đọc";

    private String title;
    private String author;
    private String category;
    private String status;
    private float rating;
    private String note;

    public Book(String title, String author, String category, String status, float rating, String note) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.rating = rating;
        this.note = note;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getNote() { return note; }

    // Chuỗi sao, ví dụ 4 sao -> ★★★★☆
    public String getStars() {
        int n = Math.round(rating);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) sb.append(i <= n ? "★" : "☆");
        return sb.toString();
    }

    // Nền và màu chữ của nhãn tình trạng
    public int getStatusBg() {
        if (status.equals(DA_DOC)) return R.drawable.chip_read;
        if (status.equals(DANG_DOC)) return R.drawable.chip_reading;
        return R.drawable.chip_unread;
    }

    public String getStatusColor() {
        if (status.equals(DA_DOC)) return "#2E7D32";
        if (status.equals(DANG_DOC)) return "#E65100";
        return "#616161";
    }
}