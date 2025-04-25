package hust.soict.hespi.aims.disc;

import hust.soict.hespi.aims.media.Playable;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0;

    //  Constructor đầy đủ
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, director, length);
    }
    //  Constructor
    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, null, 0, cost);  // gọi constructor chính
    }
    //  Constructor chỉ có tiêu đề
    public DigitalVideoDisc(String title) {
        this(title, null, null, 0, 0f); // gọi constructor chính
    }
    //  Phát nội dung đối với DVD
    @Override
    public void play() {
        if (getLength() > 0) {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength() + " mins");
        } else {
            System.out.println("Cannot play DVD: " + getTitle() + " (Length is 0 or less)");
        }
    }
    // toString
    @Override
    public String toString() {
        return String.format("DVD - %s | Director: %s | Length: %d mins\n",
                super.toString(), getDirector(), getLength());
    }
}
