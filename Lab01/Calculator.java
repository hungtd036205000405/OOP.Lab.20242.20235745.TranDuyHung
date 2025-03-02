package Hello;
import javax.swing.JOptionPane;
public class Calculator {
	public static void main(String[] args) {
        // Nhập hai số từ người dùng
        String strNum1 = JOptionPane.showInputDialog("Enter the first number:");
        String strNum2 = JOptionPane.showInputDialog("Enter the second number:");

        // Chuyển đổi chuỗi thành số thực (double)
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        // Tính toán
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        String quotient;

        // Kiểm tra mẫu số trước khi chia
        if (num2 != 0) {
            quotient = String.valueOf(num1 / num2);
        } else {
            quotient = "Khong the chia chia so ０";
        }

        // Hiển thị kết quả
        String result = "Tong: " + sum +
                        "\nHieu: " + difference +
                        "\nTich: " + product +
                        "\nThuong: " + quotient;

        JOptionPane.showMessageDialog(null, result, "Calculation Results", JOptionPane.INFORMATION_MESSAGE);

        // Kết thúc chương trình
        System.exit(0);
    }
}
