package hust.soict.hespi.aims.screen.customer.controller;
// Hiển thị thông tin 1 sản phẩm (media) lên giao diện UI item (FXML từng ô trong GridPane của Store screen).
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.exception.PlayerException;
import hust.soict.hespi.aims.cart.Cart;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class ItemController {

    private Media media;  // Lưu media hiện tại
    private Cart cart;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnAddToCart;
    public ItemController(Cart cart) {
        this.cart = cart;  // Gán giỏ hàng từ bên ngoài
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Gán dữ liệu media vào item và cập nhật UI.
     */
    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }
    @FXML
    private void btnAddToCartClicked() {
        if (cart != null && media != null) {
            cart.addMedia(media);
        }
    }

    @FXML
    private void btnPlayClicked() {
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
