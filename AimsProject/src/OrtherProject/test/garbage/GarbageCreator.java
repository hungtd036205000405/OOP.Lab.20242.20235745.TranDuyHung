package OrtherProject.test.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// khi chạy terminal không hiện thị gì do chạy ngầm
public class GarbageCreator {
    public static void main(String[] args) {
        // Đường dẫn tuyệt đối tương ứng vị trí của test.txt
        String filename = "src/hust/soict/dsai/garbage/test.txt";
        Path filePath = Paths.get(filename);

        try {
            byte[] inputBytes = Files.readAllBytes(filePath);

            long startTime = System.currentTimeMillis();

            // Nối chuỗi theo cách tệ bằng toán tử "+"
            String outputString = "";
            for (byte b : inputBytes) {
                outputString += (char) b;  // mỗi lần + tạo ra một chuỗi mới → gây "rác"
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time (bad): " + (endTime - startTime) + "ms");

        } catch (IOException e) {
            System.err.println("❌ Không thể đọc file: " + filename);
            System.err.println("Chi tiết lỗi: " + e.getMessage());
        }
    }
}
