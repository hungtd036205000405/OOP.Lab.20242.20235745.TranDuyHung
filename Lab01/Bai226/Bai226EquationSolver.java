package Phuongtrinh;

import javax.swing.JOptionPane;

public class Bai226EquationSolver {
    public static void main(String[] args) {
        while (true) {
            String input = JOptionPane.showInputDialog(null,
                "CHỌN CÔNG CỤ:\n" +
                "1. Giải phương trình bậc nhất (1 ẩn)\n" +
                "2. Giải hệ phương trình bậc nhất (2 ẩn)\n" +
                "3. Giải phương trình bậc hai (1 ẩn)\n" +
                "4. Thoát",
                "Bài 2.2.6 - Equation Solver", JOptionPane.QUESTION_MESSAGE);

            if (input == null) return; // Người dùng bấm Cancel

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        Bai226FirstDegreeLinear.main(null);
                        break;
                    case 2:
                        Bai226SystemFirstDegreeLinear.main(null);
                        break;
                    case 3:
                        Bai226SecondDegreeLinear.main(null);
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ!");
            }
        }
    }
}
