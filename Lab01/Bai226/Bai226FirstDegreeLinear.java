package Phuongtrinh;

import javax.swing.JOptionPane;

public class Bai226FirstDegreeLinear {
    public static void main(String[] args) {
        String inputA = JOptionPane.showInputDialog("Nhập a:");
        String inputB = JOptionPane.showInputDialog("Nhập b:");
        
        if (inputA == null || inputB == null) return; // Người dùng bấm Cancel

        double a = Double.parseDouble(inputA);
        double b = Double.parseDouble(inputB);

        String result;
        if (a == 0) {
            if (b == 0) {
                result = "Phương trình có vô số nghiệm (a = 0, b = 0).";
            } else {
                result = "Phương trình vô nghiệm (a = 0, b ≠ 0).";
            }
        } else {
            double x = -b / a;
            result = "Nghiệm x = " + x;
        }

        JOptionPane.showMessageDialog(null, result);
    }
}
