package Hello;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số phần tử của mảng
        int n = scanner.nextInt();
        int[] myArray = new int[n];

        // Nhập các phần tử của mảng
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }

        // Sắp xếp mảng tăng dần
        Arrays.sort(myArray);

        // Tính tổng các phần tử
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }
        double average = (double) sum / n;

        // In kết quả
        System.out.println(Arrays.toString(myArray));
        System.out.println(sum);
        System.out.printf("%.2f\n", average);

        scanner.close();
    }
}
