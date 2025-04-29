package OrtherProject.garbage.garbage;

import java.util.Random;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        Random r = new Random(123);

        // Cách 1: Dùng String + (kém hiệu quả)
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 65536; i++) {
            s += r.nextInt(2); // nối chuỗi bằng +
        }
        System.out.println("Time with String + : " + (System.currentTimeMillis() - start) + "ms");

        // Cách 2: Dùng StringBuilder (hiệu quả hơn)
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 65536; i++) {
            sb.append(r.nextInt(2));
        }
        System.out.println("Time with StringBuilder: " + (System.currentTimeMillis() - start) + "ms");

        // Cách 3: Dùng StringBuffer (an toàn thread)
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < 65536; i++) {
            sbuf.append(r.nextInt(2));
        }
        System.out.println("Time with StringBuffer: " + (System.currentTimeMillis() - start) + "ms");
    }
}
