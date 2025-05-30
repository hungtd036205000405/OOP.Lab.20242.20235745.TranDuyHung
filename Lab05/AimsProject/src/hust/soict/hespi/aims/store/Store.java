package hust.soict.hespi.aims.store;

import hust.soict.hespi.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    // Constructor
    public Store() {
        // Khởi tạo cửa hàng với danh sách media rỗng
    }
    // Thêm media vào cửa hàng
    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Added to store: " + media.getTitle());
        } else {
            System.out.println("Media already exists in store: " + media.getTitle());
        }
    }

    // Xóa media khỏi cửa hàng
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed from store: " + media.getTitle());
        } else {
            System.out.println("Media not found in store: " + media.getTitle());
        }
    }


    // In toàn bộ media trong cửa hàng
    public void printStore() {
        System.out.println("************** STORE **************");
        int index = 1;
        for (Media media : itemsInStore) {
            System.out.println(index++ + ". " + media + "\n");
        }
        System.out.println("***********************************");
    }



    // Tìm media theo tiêu đề
    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }
    // danh sách tất cả media hiện có
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}