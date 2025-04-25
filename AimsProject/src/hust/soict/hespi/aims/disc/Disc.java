package hust.soict.hespi.aims.disc;

import hust.soict.hespi.aims.media.Media;

public class Disc extends Media {
    // lop cha cua 2 lop con : CD va DVD
    private String director;
    private int length;

    public Disc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }
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
}
