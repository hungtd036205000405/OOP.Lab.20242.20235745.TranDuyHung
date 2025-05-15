package hust.soict.hespi.aims.cd;

import hust.soict.hespi.aims.media.Playable;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    // ghi de impliment
    public void play() {
        if (length > 0) {
            System.out.println("Playing track: " + title + " | Length: " + length + " mins");
        } else {
            System.out.println("Cannot play track: " + title + " (Invalid length)");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) return false;
        Track other = (Track) obj;
        return title != null &&
                title.equalsIgnoreCase(other.title) &&
                length == other.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title != null ? title.toLowerCase() : null, length);
    }

    @Override
    public String toString() {
        return title + " (" + length + " mins)";
    }
}
