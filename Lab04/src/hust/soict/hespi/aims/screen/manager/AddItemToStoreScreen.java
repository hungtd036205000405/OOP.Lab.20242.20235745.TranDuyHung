package hust.soict.hespi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hust.soict.hespi.aims.store.Store;
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;      // Đối tượng Store chứa danh sách các sản phẩm

    public AddItemToStoreScreen(Store store, String title) {
        this.store = store;

        // Thiết lập Layout cho cửa sổ chính
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Thêm các phần tử vào cửa sổ
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        // Thiết lập các thuộc tính cho cửa sổ
        setTitle(title);
        setSize(1024, 768);
        setLocationRelativeTo(null);  // Đặt cửa sổ ở giữa màn hình
        setVisible(true);  // Hiển thị cửa sổ
    }

    // Phương thức tạo phần "North" gồm menu bar và header
    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar()); // Thêm menu bar
        north.add(createHeader());  // Thêm header
        return north;
    }

    // Phương thức tạo menu bar
    private JMenuBar createMenuBar() {
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
    private JPanel createHeader() {
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

    // Phương thức tạo phần "Center" (có thể thay đổi cho mỗi loại sản phẩm)
    protected abstract JPanel createCenter();

    // Phương thức hiển thị màn hình "View Store"
    private void showViewStoreScreen() {
        // Hiển thị thông báo "Viewing Store"
        JOptionPane.showMessageDialog(this, "Viewing Store", "Store", JOptionPane.INFORMATION_MESSAGE);
    }
}
