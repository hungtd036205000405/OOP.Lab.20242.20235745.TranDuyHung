package Hello;
import java.util.*;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> monthMap = new HashMap<>();
        
        // Danh sách tháng và số tương ứng
        String[][] months = {
            {"January", "Jan.", "Jan", "1"},
            {"February", "Feb.", "Feb", "2"},
            {"March", "Mar.", "Mar", "3"},
            {"April", "Apr.", "Apr", "4"},
            {"May", "May", "May", "5"},
            {"June", "Jun.", "Jun", "6"},
            {"July", "Jul.", "Jul", "7"},
            {"August", "Aug.", "Aug", "8"},
            {"September", "Sept.", "Sep", "9"},
            {"October", "Oct.", "Oct", "10"},
            {"November", "Nov.", "Nov", "11"},
            {"December", "Dec.", "Dec", "12"}
        };

        // Gán giá trị vào HashMap để kiểm tra nhanh
        for (int i = 0; i < months.length; i++) {
            for (String alias : months[i]) {
                monthMap.put(alias.toLowerCase(), i + 1);
            }
        }

        int month = -1, year = -1;

        // Nhập tháng hợp lệ
        while (month == -1) {
            System.out.print("Enter a month (name, abbreviation, or number): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (monthMap.containsKey(input)) {
                month = monthMap.get(input);
            } else {
                System.out.println("Invalid month! Please enter again.");
            }
        }

        // Nhập năm hợp lệ
        while (year < 0) {
            System.out.print("Enter a valid year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year < 0) {
                    System.out.println("Year must be non-negative.");
                    year = -1;
                }
            } else {
                System.out.println("Invalid year! Please enter a number.");
                scanner.next();
            }
        }

        // Kiểm tra năm nhuận
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int days = 0;

        // Sửa switch-case để tương thích với Java cũ
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                days = isLeapYear ? 29 : 28;
                break;
            default:
                days = 0; // Không xảy ra vì đã kiểm tra đầu vào
        }

        System.out.println("Month " + month + " of year " + year + " has " + days + " days.");
    }
}
