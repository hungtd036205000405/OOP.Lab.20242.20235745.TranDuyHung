package hust.soict.hespi.swing.screen.manager;

import hust.soict.hespi.swing.store.Store;
import hust.soict.hespi.swing.cd.CompactDisc;  // Giả sử bạn có lớp CompactDisc

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField titleField;  // Trường nhập tên
    private JTextField priceField;  // Trường nhập giá

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD");  // Gọi constructor của lớp cha và đặt tiêu đề cho cửa sổ
    }

    @Override
    protected JPanel createCenter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Các trường nhập liệu nằm dọc
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Thêm padding cho panel

        // Tạo các thành phần nhập liệu cho Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Sử dụng FlowLayout cho title
        titlePanel.add(new JLabel("Enter CD Title:"));
        titleField = new JTextField(30);  // Chiều rộng cho ô nhập liệu
        titleField.setPreferredSize(new Dimension(400, 40));  // Tăng chiều cao cho ô nhập liệu
        titlePanel.add(titleField);
        panel.add(titlePanel);  // Thêm panel chứa title vào

        panel.add(Box.createVerticalStrut(10));  // Khoảng cách giữa các phần tử

        // Tạo các thành phần nhập liệu cho Price
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Sử dụng FlowLayout cho price
        pricePanel.add(new JLabel("Enter Price:"));
        priceField = new JTextField(30);  // Chiều rộng cho ô nhập liệu
        priceField.setPreferredSize(new Dimension(400, 40));  // Tăng chiều cao cho ô nhập liệu
        pricePanel.add(priceField);
        panel.add(pricePanel);  // Thêm panel chứa price vào

        panel.add(Box.createVerticalStrut(30));  // Khoảng cách giữa các phần tử

        // Tạo một panel để chứa các nút "Add Item" và "Back" nằm ngang
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Các nút nằm ngang

        // Nút "Add Item"
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();  // Khi nhấn nút, thêm sản phẩm vào cửa hàng
            }
        });
        buttonPanel.add(addButton);

        // Nút "Back"
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToViewStore();  // Quay lại màn hình "View Store"
            }
        });
        buttonPanel.add(backButton);

        panel.add(buttonPanel);  // Thêm panel chứa các nút vào

        return panel;
    }

    // Phương thức thêm CD vào cửa hàng
    private void addItem() {
        String title = titleField.getText();
        double price;
        try {
            price = Double.parseDouble(priceField.getText());  // Chuyển đổi giá nhập vào từ String thành double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo CD mới và thêm vào cửa hàng
        CompactDisc cd = new CompactDisc(title, (float) price);  // Giả sử bạn có lớp CompactDisc
        store.addMedia(cd);  // Thêm CD vào cửa hàng

        // Hiển thị thông báo thành công
        JOptionPane.showMessageDialog(this, "CD added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Xóa các trường nhập liệu sau khi thêm CD
        titleField.setText("");
        priceField.setText("");
    }

    // Phương thức quay lại màn hình "View Store"
    private void backToViewStore() {
        new StoreManagerScreen(store);  // Quay lại màn hình Store Manager (hoặc bạn có thể chuyển sang màn hình khác)
        this.setVisible(false);  // Ẩn màn hình Add CD
    }
}
