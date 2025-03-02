package Phuongtrinh;

import javax.swing.JOptionPane;

public class Bai226SystemFirstDegreeLinear {
    public static void main(String[] args) {
        String inputA11 = JOptionPane.showInputDialog("Nhập a11:");
        String inputA12 = JOptionPane.showInputDialog("Nhập a12:");
        String inputB1 = JOptionPane.showInputDialog("Nhập b1:");
        String inputA21 = JOptionPane.showInputDialog("Nhập a21:");
        String inputA22 = JOptionPane.showInputDialog("Nhập a22:");
        String inputB2 = JOptionPane.showInputDialog("Nhập b2:");

        if (inputA11 == null || inputA12 == null || inputB1 == null ||
            inputA21 == null || inputA22 == null || inputB2 == null) return;

        double a11 = Double.parseDouble(inputA11);
        double a12 = Double.parseDouble(inputA12);
        double b1 = Double.parseDouble(inputB1);
        double a21 = Double.parseDouble(inputA21);
        double a22 = Double.parseDouble(inputA22);
        double b2 = Double.parseDouble(inputB2);

        double D  = a11 * a22 - a12 * a21;
        double D1 = b1 * a22 - a12 * b2;
        double D2 = a11 * b2 - b1 * a21;

        String result;
        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                result = "Hệ phương trình có vô số nghiệm.";
            } else {
                result = "Hệ phương trình vô nghiệm.";
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            result = "Hệ có nghiệm duy nhất:\n" + "x1 = " + x1 + ", x2 = " + x2;
        }

        JOptionPane.showMessageDialog(null, result);
    }
}
