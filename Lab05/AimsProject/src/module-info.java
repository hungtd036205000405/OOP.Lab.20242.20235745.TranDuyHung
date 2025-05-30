module Lab05.bai2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens hust.soict.hespi.aims.screen.customer.controller to javafx.fxml;
    opens hust.soict.hespi.aims.screen.customer.view to javafx.fxml;
    opens hust.soict.hespi.aims.test.screen.customer.store to javafx.graphics, javafx.fxml;

    exports hust.soict.hespi.aims.store;
    exports hust.soict.hespi.aims.media;
    exports hust.soict.hespi.aims.cart;
    exports hust.soict.hespi.aims.screen.customer.controller;
}
