package Hello;
import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;
        String monthInput;
        int month = -1;

        // Danh sách tháng hợp lệ
        String[] months = {
            "January", "Jan.", "Jan", "1",
            "February", "Feb.", "Feb", "2",
            "March", "Mar.", "Mar", "3",
            "April", "Apr.", "Apr", "4",
            "May", "5",
            "June", "Jun.", "Jun", "6",
            "July", "Jul.", "Jul", "7",
            "August", "Aug.", "Aug", "8",
            "September", "Sept.", "Sep", "9",
            "October", "Oct.", "Oct", "10",
            "November", "Nov.", "Nov", "11",
            "December", "Dec.", "Dec", "12"
        };

        // Số ngày trong năm thường
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Nhập tháng
        while (month == -1) {
            System.out.print("Nhập tháng (tên đầy đủ, viết tắt hoặc số): ");
            monthInput = scanner.next();

            // Kiểm tra tháng hợp lệ
            for (int i = 0; i < months.length; i++) {
                if (monthInput.equalsIgnoreCase(months[i])) {
                    month = (i / 4) + 1;
                    break;
                }
            }

            if (month == -1) {
                System.out.println("Tháng không hợp lệ, vui lòng nhập lại.");
            }
        }

        // Nhập năm
        while (true) {
            System.out.print("Nhập năm (số nguyên dương): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) {
                    break;
                }
            } else {
                scanner.next(); // Xóa dữ liệu nhập sai
            }
            System.out.println("Năm không hợp lệ, vui lòng nhập lại.");
        }

        // Kiểm tra năm nhuận
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (isLeapYear && month == 2) {
            System.out.println("Tháng " + month + " năm " + year + " có 29 ngày.");
        } else {
            System.out.println("Tháng " + month + " năm " + year + " có " + daysInMonth[month - 1] + " ngày.");
        }

        scanner.close();
    }
}
