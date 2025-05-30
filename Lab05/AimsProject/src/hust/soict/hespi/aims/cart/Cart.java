package hust.soict.hespi.aims.cart;

import hust.soict.hespi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;

    // Sử dụng ObservableList để hỗ trợ cập nhật UI tự động
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    // Thêm một media vào giỏ hàng
    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full!");
            return;
        }

        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("Added to cart: " + media.getTitle());
        } else {
            System.out.println("Item already in cart: " + media.getTitle());
        }
    }

    // Xóa media khỏi giỏ hàng
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        } else {
            System.out.println("Item not found in cart: " + media.getTitle());
        }
    }

    // Tính tổng tiền trong giỏ hàng
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // In thông tin giỏ hàng ra console
    public void print() {
        System.out.println("*********************** CART ***********************");
        System.out.println("Ordered Items:");
        int index = 1;
        for (Media media : itemsOrdered) {
            System.out.println(index++ + ". " + media);
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("****************************************************");
    }

    // Trả về ObservableList media đã đặt hàng (dùng để bind với UI)
    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    // Sắp xếp giỏ hàng theo Title rồi Cost
    public void sortByTitleCost() {
        // Tạo bản sao danh sách để sắp xếp
        List<Media> sortedList = new ArrayList<>(itemsOrdered);
        Collections.sort(sortedList, Media.COMPARE_BY_TITLE_COST);

        // Cập nhật lại ObservableList
        itemsOrdered.setAll(sortedList);
    }

    // Sắp xếp giỏ hàng theo Cost rồi Title
    public void sortByCostTitle() {
        List<Media> sortedList = new ArrayList<>(itemsOrdered);
        Collections.sort(sortedList, Media.COMPARE_BY_COST_TITLE);

        itemsOrdered.setAll(sortedList);
    }
}
