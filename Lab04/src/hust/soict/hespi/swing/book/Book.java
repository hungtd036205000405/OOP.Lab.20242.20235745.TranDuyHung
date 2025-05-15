package hust.soict.hespi.swing.book;

import hust.soict.hespi.swing.media.Media;
import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor
    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }
    public Book(String title, float cost) {
        this(0, title, null, cost);  // Gọi constructor đầy đủ với id mặc định là 0 và category là null
    }

    // Thêm tác giả không trùng lặp
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added author: " + authorName);
        } else {
            System.out.println("Author already exists: " + authorName);
        }
    }

    // Xoá tác giả nếu tồn tại
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author not found: " + authorName);
        }
    }

    // Tính độ dài nội dung dựa trên số từ trong tiêu đề
    public int getContentLength() {
        return getTitle() != null ? getTitle().split("\\s+").length : 0;
    }

    // (Optional) Getter cho danh sách tác giả
    public ArrayList<String> getAuthors() {
        return authors;
    }

    // In thông tin Book
    @Override
    public String toString() {
        return String.format("Book - %s | Authors: %s | Content Length: %d tokens",
                super.toString(), String.join(", ", authors), getContentLength());
    }
}
