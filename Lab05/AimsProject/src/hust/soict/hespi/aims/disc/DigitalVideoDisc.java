package hust.soict.hespi.aims.disc;

import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }
    // Constructor đầy đủ
    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost);  // Gọi constructor của lớp cha Media
        this.director = director;
        this.length = length;
    }


    // Constructor đơn giản
    public DigitalVideoDisc(String title, String category, float cost) {
        this(0, title, category, null, 0, cost);  // Gọi constructor đầy đủ với id mặc định là 0
    }

    // Constructor chỉ có tiêu đề
    public DigitalVideoDisc(String title) {
        this(0, title, null, null, 0, 0f);  // Gọi constructor đầy đủ với id mặc định là 0
    }
    // Constructor nhận tiêu đề và giá (thêm constructor này để sửa lỗi)
    public DigitalVideoDisc(String title, double cost) {
        this(0, title, null, null, 0, (float) cost);  // Gọi constructor đầy đủ với id mặc định là 0, và chuyển đổi giá sang kiểu float
    }

    // Getter and Setter cho director và length
    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // Phương thức play
    @Override
    public void play() throws PlayerException {
        if (length <= 0) {
            String errorMsg = "ERROR: Cannot play DVD: " + getTitle() + " (Length is 0 or less)";
            System.err.println(errorMsg);
            throw new PlayerException(errorMsg);
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength() + " mins");
    }

    // Override toString để hiển thị thông tin đầy đủ
    @Override
    public String toString() {
        return String.format("DVD - %s | Director: %s | Length: %d mins\n",
                super.toString(), getDirector(), getLength());
    }
}
