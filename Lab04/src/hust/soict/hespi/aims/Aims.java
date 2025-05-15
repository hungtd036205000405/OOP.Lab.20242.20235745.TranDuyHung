package hust.soict.hespi.aims;

import hust.soict.hespi.aims.book.Book;
import hust.soict.hespi.aims.disc.DigitalVideoDisc;
import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.screen.manager.StoreManagerScreen;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store();

        // Thêm Book (dùng constructor id, title, category, cost)
        Book book1 = new Book(1, "Harry Potter and the Philosopher's Stone", "Fantasy", 3.0f);
        book1.addAuthor("J.K. Rowling");
        store.addMedia(book1);

        Book book2 = new Book(2, "The Hobbit", "Fantasy", 4.5f);
        book2.addAuthor("J.R.R. Tolkien");
        store.addMedia(book2);

        Book book3 = new Book(3, "The Lord of the Rings", "Fantasy", 6.0f);
        book3.addAuthor("J.R.R. Tolkien");
        store.addMedia(book3);

        // Thêm DigitalVideoDisc (dùng constructor đầy đủ)
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(4, "The Matrix", "Action", "The Wachowskis", 136, 7.5f);
        store.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(5, "Inception", "Sci-Fi", "Christopher Nolan", 148, 8.0f);
        store.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(6, "Interstellar", "Sci-Fi", "Christopher Nolan", 169, 9.0f);
        store.addMedia(dvd3);

        // Thêm DigitalVideoDisc (dùng constructor đơn giản)
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Joker", "Drama", 6.5f);
        store.addMedia(dvd4);

        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Parasite");
        dvd5.setCategory("Thriller");
        dvd5.setCost(7.2f);
        dvd5.setDirector("Bong Joon-ho");
        dvd5.setLength(132);
        store.addMedia(dvd5);

        // Mở giao diện StoreManager
        new StoreManagerScreen(store);
    }
}
