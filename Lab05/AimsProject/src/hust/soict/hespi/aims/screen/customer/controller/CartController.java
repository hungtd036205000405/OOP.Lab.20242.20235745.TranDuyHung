package hust.soict.hespi.aims.screen.customer.controller;

import hust.soict.hespi.aims.cart.Cart;
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.exception.PlayerException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CartController {

    private Cart cart;
    private Store store;
    private ObservableList<Media> itemsOrdered;

    // Chỉ giữ constructor không tham số để FXMLLoader hoạt động
    public CartController() {
        // Constructor mặc định bắt buộc phải có để FXMLLoader load được controller
    }

    // Thêm setter để truyền cart từ bên ngoài
    public void setCart(Cart cart) {
        this.cart = cart;
        if (cart != null) {
            this.itemsOrdered = cart.getItemsOrdered();
            if (tblMedia != null) {
                tblMedia.setItems(itemsOrdered);
                updateTotalCost();
            }
        }
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @FXML
    private Button btnViewStore;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnPlay;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Label costLabel;

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(cellData ->
            javafx.beans.binding.Bindings.createStringBinding(
                () -> String.valueOf(cellData.getValue().getId())));
        colMediaTitle.setCellValueFactory(cellData ->
            javafx.beans.binding.Bindings.createStringBinding(
                () -> cellData.getValue().getTitle()));
        colMediaCategory.setCellValueFactory(cellData ->
            javafx.beans.binding.Bindings.createStringBinding(
                () -> cellData.getValue().getCategory()));
        colMediaCost.setCellValueFactory(cellData ->
            javafx.beans.binding.Bindings.createObjectBinding(
                () -> cellData.getValue().getCost()));

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> updateButtonBar(newValue));
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    @FXML
    public void btnRemovePressed() {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null && cart != null) {
            cart.removeMedia(selected);
            updateTotalCost();
        }
    }

    @FXML
    public void btnPlayPressed() {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
            try {
                ((Playable) selected).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void btnViewStorePressed() {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hespi/aims/screen/customer/view/store_gui.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            Parent root = fxmlLoader.load();

            // Get the controller and set store & cart
            ViewStoreController viewStoreController = fxmlLoader.getController();
            viewStoreController.setStore(this.store);
            viewStoreController.setCart(this.cart);

            // Create new scene & stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Store");
            stage.setScene(scene);

            // Close current cart window
            Stage currentStage = (Stage) btnViewStore.getScene().getWindow();
            currentStage.close();

            // Show store window
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading store_gui.fxml: " + e.getMessage());
        }
    }

    @FXML
    public void btnPlaceOrderPressed() {
        if (cart != null) {
            cart.getItemsOrdered().clear();
            updateTotalCost();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Order placed successfully!");
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        if (cart != null) {
            costLabel.setText(String.format("%.2f $", cart.totalCost()));
        } else {
            costLabel.setText("0 $");
        }
    }
}
