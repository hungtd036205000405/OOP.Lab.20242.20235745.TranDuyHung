package Hello;

//Example 5: ShowTwoNumbers.java
import javax.swing.JOptionPane;

public class ShowTwoNumber {
 public static void main(String[] args) {
     // Khai bao hai chuoi de nhan so tu nguoi dung
     String strNum1, strNum2;
     // Chuoi thong bao ket qua
     String strNotification = "You've just entered: ";

     // Hien thi hop thoai nhap so thu nhat
     strNum1 = JOptionPane.showInputDialog(null,
             "Please input the first number: ", "Input the first number",
             JOptionPane.INFORMATION_MESSAGE);

     // Cap nhat chuoi thong bao
     strNotification += strNum1 + " and ";

     // Hien thi hop thoai nhap so thu hai
     strNum2 = JOptionPane.showInputDialog(null,
             "Please input the second number: ", "Input the second number",
             JOptionPane.INFORMATION_MESSAGE);

     // Cap nhat chuoi thong bao
     strNotification += strNum2;

     // Hien thi hop thoai thong bao ket qua
     JOptionPane.showMessageDialog(null, strNotification,
             "Show two numbers", JOptionPane.INFORMATION_MESSAGE);

     // Ket thuc chuong trinh
     System.exit(0);
 }
}
