package hust.soict.hespi.aims.screen.customer.controller;

import hust.soict.hespi.aims.cart.Cart;
import hust.soict.hespi.aims.store.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewStoreController {
    private Store store;
    private Cart cart;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnViewCart;

    public ViewStoreController() {
        // No-arg constructor for FXML
    }

    public void setStore(Store store) {
        this.store = store;
        if (this.cart != null) {
            loadItemsToGrid();
        }
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (this.store != null) {
            loadItemsToGrid();
        }
    }

    private void loadItemsToGrid() {
        if (gridPane == null || store == null || cart == null) {
            System.out.println("Cannot load items: gridPane, store, or cart is null");
            return;
        }

        System.out.println("Loading " + store.getItemsInStore().size() + " items to grid");

        // Clear existing items
        gridPane.getChildren().clear();

        final String ITEM_FXML_FILE_PATH = "/hust/soict/hespi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 0;

        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));

                // Create and set controller before loading FXML
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);

                // Load the FXML
                AnchorPane anchorPane = fxmlLoader.load();

                // Set data for the item
                itemController.setData(store.getItemsInStore().get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                // Add to grid
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

                System.out.println("Added item: " + store.getItemsInStore().get(i).getTitle());

            } catch (IOException e) {
                System.err.println("Error loading item FXML: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        System.out.println("ViewStoreController initialized");
    }

    @FXML
    public void btnViewCartPressed() {
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hespi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            Parent root = fxmlLoader.load();

            // Lấy controller của Cart và set cart vào đó
            CartController cartController = fxmlLoader.getController();
            cartController.setCart(this.cart);  // Truyền cart hiện tại
            cartController.setStore(this.store); // Truyền store để có thể quay lại

            // Tạo scene mới
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cart");
            stage.setScene(scene);

            // Đóng cửa sổ store hiện tại
            Stage currentStage = (Stage) btnViewCart.getScene().getWindow();
            currentStage.close();

            // Hiển thị cửa sổ cart
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Cart.fxml: " + e.getMessage());
        }
    }
}

