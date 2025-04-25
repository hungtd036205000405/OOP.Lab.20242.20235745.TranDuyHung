package hust.soict.hespi.aims.cart;

import hust.soict.hespi.aims.media.Media;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    // Thêm một media
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

    // Thêm nhiều media (varargs)
    public void addMedia(Media... mediaList) {
        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    // Xoá media
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle());
        } else {
            System.out.println("Item not found in cart: " + media.getTitle());
        }
    }

    // Tính tổng tiền
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // In thông tin giỏ hàng
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

    // Lấy danh sách media
    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    //  Sắp xếp
    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }
}
