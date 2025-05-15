package hust.soict.hespi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.Media;

public class StoreManagerScreen extends JFrame {
    private Store store;  // Đối tượng Store chứa danh sách các sản phẩm

    public StoreManagerScreen(Store store) {
        this.store = store;

        // Thiết lập Layout cho cửa sổ chính
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Thêm các phần tử vào cửa sổ
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        // Thiết lập các thuộc tính cho cửa sổ
        setTitle("Store Manager");
        setSize(1024, 768);
        setLocationRelativeTo(null);  // Đặt cửa sổ ở giữa màn hình
        setVisible(true);  // Hiển thị cửa sổ
    }

    // Phương thức tạo phần "North" gồm menu bar và header
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar()); // Thêm menu bar
        north.add(createHeader());  // Thêm header
        return north;
    }

    // Phương thức tạo menu bar
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreItem = new JMenuItem("View Store");

        // Sự kiện khi chọn "View Store"
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showViewStoreScreen(); // Hiển thị màn hình xem cửa hàng
            }
        });

        // Tạo menu "Update Store"
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem addCDItem = new JMenuItem("Add CD");
        JMenuItem addDVDItem = new JMenuItem("Add DVD");

        // Sự kiện khi chọn "Add Book"
        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBookScreen(); // Hiển thị màn hình thêm sách
            }
        });

        // Sự kiện khi chọn "Add CD"
        addCDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCDScreen(); // Hiển thị màn hình thêm CD
            }
        });

        // Sự kiện khi chọn "Add DVD"
        addDVDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDVDScreen(); // Hiển thị màn hình thêm DVD
            }
        });

        // Thêm các mục vào menu
        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(viewStoreItem);
        menu.add(smUpdateStore);

        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;  // Trả về menu bar đã tạo
    }

    // Phương thức tạo header cho cửa sổ
    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);  // Đặt màu cho tiêu đề

        // Thêm các thành phần vào header
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());  // Tạo khoảng trắng để tiêu đề căn giữa
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // Phương thức tạo phần "Center" chứa các sản phẩm
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 10, 10));  // Tạo grid 3x3 cho các sản phẩm

        // Lấy danh sách các sản phẩm trong cửa hàng
        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < 9 && i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));  // Tạo MediaStore cho từng sản phẩm
            center.add(cell);  // Thêm sản phẩm vào grid
        }

        return center;
    }

    // Phương thức hiển thị màn hình "View Store"
    private void showViewStoreScreen() {
        // Hiển thị thông báo "Viewing Store"
        JOptionPane.showMessageDialog(this, "Viewing Store", "Store", JOptionPane.INFORMATION_MESSAGE);
    }

    // Phương thức hiển thị màn hình "Add Book"
    private void showAddBookScreen() {
        new AddBookToStoreScreen(store).setVisible(true);  // Hiển thị màn hình thêm sách
    }

    // Phương thức hiển thị màn hình "Add CD"
    private void showAddCDScreen() {
        new AddCompactDiscToStoreScreen(store).setVisible(true);  // Hiển thị màn hình thêm CD
    }

    // Phương thức hiển thị màn hình "Add DVD"
    private void showAddDVDScreen() {
        new AddDigitalVideoDiscToStoreScreen(store).setVisible(true);  // Hiển thị màn hình thêm DVD
    }

    // Phương thức main để chạy ứng dụng
//    public static void main(String[] args) {
//        // Tạo một đối tượng Store mẫu
//        Store store = new Store(); // Bạn cần thay thế bằng logic thực tế để tạo đối tượng Store
//        new StoreManagerScreen(store);  // Khởi tạo và hiển thị cửa sổ StoreManagerScreen
//    }
}
