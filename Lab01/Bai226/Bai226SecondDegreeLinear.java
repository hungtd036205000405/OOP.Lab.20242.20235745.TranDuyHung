package Phuongtrinh;

import javax.swing.JOptionPane;

public class Bai226SecondDegreeLinear {
    public static void main(String[] args) {
        String inputA = JOptionPane.showInputDialog("Nhập a:");
        String inputB = JOptionPane.showInputDialog("Nhập b:");
        String inputC = JOptionPane.showInputDialog("Nhập c:");

        if (inputA == null || inputB == null || inputC == null) return;

        double a = Double.parseDouble(inputA);
        double b = Double.parseDouble(inputB);
        double c = Double.parseDouble(inputC);

        String result;
        if (a == 0) {
            if (b == 0) {
                result = (c == 0) ? "Phương trình có vô số nghiệm." : "Phương trình vô nghiệm.";
            } else {
                double x = -c / b;
                result = "Nghiệm bậc nhất: x = " + x;
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                result = "Phương trình vô nghiệm.";
            } else if (delta == 0) {
                double x = -b / (2 * a);
                result = "Phương trình có nghiệm kép: x1 = x2 = " + x;
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                result = "Phương trình có 2 nghiệm phân biệt:\n" + "x1 = " + x1 + "    | x2 = " + x2;
            }
        }

        JOptionPane.showMessageDialog(null, result);
    }
}
