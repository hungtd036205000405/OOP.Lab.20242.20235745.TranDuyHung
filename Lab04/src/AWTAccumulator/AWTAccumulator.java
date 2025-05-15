package AWTAccumulator;// Import các thư viện cần thiết trong AWT và xử lý sự kiện
import java.awt.*;              // Giao diện AWT (Frame, Label, TextField, GridLayout, v.v.)
import java.awt.event.*;        // Thư viện để xử lý sự kiện (ActionListener, ActionEvent)
// Lớp AWTAccumulator.AWTAccumulator kế thừa từ Frame để tạo giao diện AWT
public class AWTAccumulator extends Frame {
    private TextField tfInput;   // Ô nhập số nguyên
    private TextField tfOutput;  // Ô hiển thị tổng tích lũy
    private int sum = 0;         // Biến lưu tổng các số đã nhập

    // Constructor - thiết lập giao diện và các thành phần sự kiện
    public AWTAccumulator() {
        // Sử dụng bố cục 2 hàng 2 cột
        setLayout(new GridLayout(2, 2));

        // Thêm nhãn và ô nhập số nguyên
        add(new Label("Enter an Integer: "));   // Nhãn hướng dẫn nhập
        tfInput = new TextField(10);            // Tạo ô nhập với độ rộng 10 ký tự
        add(tfInput);                           // Thêm vào giao diện
        tfInput.addActionListener(new TFInputListener()); // gọi hàm thực thi in ra kết quả

        // Thêm nhãn và ô hiển thị tổng cộng dồn
        add(new Label("The Accumulated Sum is: ")); // Nhãn hiển thị kết quả
        tfOutput = new TextField(10);               // Tạo ô kết quả
        tfOutput.setEditable(false);                // Không cho chỉnh sửa ô này
        add(tfOutput);                              // Thêm vào giao diện

        // Thiết lập tiêu đề, kích thước cửa sổ và hiển thị
        setTitle("AWT Accumulator");  // Tiêu đề cửa sổ
        setSize(350, 120);            // Kích thước cửa sổ
        setVisible(true);             // Hiển thị cửa sổ
    }

    // Hàm main - tạo đối tượng giao diện
    public static void main(String[] args) {
        new AWTAccumulator();
    }

    // Lớp lắng nghe sự kiện khi nhấn Enter trong tfInput
    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Lấy số nguyên từ tfInput
            int numberIn = Integer.parseInt(tfInput.getText());

            // Cộng dồn vào biến sum
            sum += numberIn;

            // Xóa ô nhập và cập nhật kết quả vào ô hiển thị
            tfInput.setText("");
            tfOutput.setText(sum + ""); // Ép kiểu về chuỗi để hiển thị
        }
    }
}


