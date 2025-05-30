package hust.soict.hespi.aims.test.screen.customer.store;

import hust.soict.hespi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.book.Book;
import hust.soict.hespi.aims.cd.CompactDisc;
import hust.soict.hespi.aims.disc.DigitalVideoDisc;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {

    private static Store store;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hespi/aims/screen/customer/view/store_gui.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        if (fxmlLoader.getLocation() == null) {
            System.err.println("Cannot find store_gui.fxml at path: " + STORE_FXML_FILE_PATH);
            return;
        }
        Parent root = fxmlLoader.load();

        // Lấy controller do FXML tự động tạo
        ViewStoreController viewStoreController = fxmlLoader.getController();
        viewStoreController.setStore(store);
        viewStoreController.setCart(new hust.soict.hespi.aims.cart.Cart());

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        Book book2 = new Book(2, "The Hobbit", "Fantasy", 4.5f);
        book2.addAuthor("J.R.R. Tolkien");
        store.addMedia(book2);

        Book book3 = new Book(3, "The Lord of the Rings", "Fantasy", 6.0f);
        book3.addAuthor("J.R.R. Tolkien");
        store.addMedia(book3);

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(4, "The Matrix", "Action", "The Wachowskis", 136, 7.5f);
        store.addMedia(dvd1);

        CompactDisc cd1 = new CompactDisc(1, "Abbey Road", "Rock", 2000, "The Beatles", "John Lennon");
        store.addMedia(cd1);

        Book book1 = new Book(1, "1984", "Dystopian", 50.0f);
        book1.addAuthor("George Orwell");
        store.addMedia(book1);

        CompactDisc cd2 = new CompactDisc(2, "Dark Side of the Moon", "Rock", 1973, "Pink Floyd", "Roger Waters");
        store.addMedia(cd2);

        CompactDisc cd3 = new CompactDisc(3, "Back in Black", "Rock", 1980, "AC/DC", "Brian Johnson");
        store.addMedia(cd3);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(5, "Inception", "Sci-Fi", "Christopher Nolan", 148, 8.0f);
        store.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(6, "Interstellar", "Sci-Fi", "Christopher Nolan", 169, 9.0f);
        store.addMedia(dvd3);

        launch(args);
    }
}

