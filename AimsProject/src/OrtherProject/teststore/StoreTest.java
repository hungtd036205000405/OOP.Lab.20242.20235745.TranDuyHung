package OrtherProject.teststore;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.disc.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store myStore = new Store();

        // Tạo các DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(5, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(7, "Aladdin", "Animation", "Guy Ritchie", 90, 24.99f);

        // Thêm vào cửa hàng
        myStore.addMedia(dvd1);
        myStore.addMedia(dvd2);

        // In thông tin cửa hàng
        myStore.printStore();

        // Xoá 1 DVD
        myStore.removeMedia(dvd1);

        // In lại cửa hàng sau khi xoá
        myStore.printStore();
    }
}
