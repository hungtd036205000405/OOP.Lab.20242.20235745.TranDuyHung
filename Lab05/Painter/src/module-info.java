module Lab05 {
    requires javafx.controls;
    requires javafx.fxml;

    opens hust.soict.program.javafx to javafx.fxml; // Mở toàn bộ package javafx (có Painter, có thể có controller sau này)
    opens hust.soict.program.javafx.controllers to javafx.fxml; // Cho phép FXML gọi Controller
    exports hust.soict.program.javafx;
    exports hust.soict.program.javafx.controllers;
}
