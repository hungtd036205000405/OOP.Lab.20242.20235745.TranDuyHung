package OrtherProject.garbage.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "src/hust/soict/dsai/garbage/test.txt";  // <-- đường dẫn đúng
        try {
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

            long startTime = System.currentTimeMillis();
            StringBuffer outputStringBuffer = new StringBuffer();

            for (byte b : inputBytes) {
                outputStringBuffer.append((char) b);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time (optimized): " + (endTime - startTime) + "ms");

        } catch (IOException e) {
            System.err.println(" Không thể đọc file: " + filename);
            System.err.println("Chi tiết lỗi: " + e.getMessage());
        }
    }
}
