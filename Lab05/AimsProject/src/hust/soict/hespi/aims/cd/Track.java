package hust.soict.hespi.aims.cd;

import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.exception.PlayerException;

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
    public void play() throws PlayerException {
        if (length <= 0) {
            String errorMsg = "ERROR: Cannot play track: " + title + " (Length is 0 or less)";
            System.err.println(errorMsg);
            throw new PlayerException(errorMsg);
        }
        System.out.println("Playing track: " + title);
        System.out.println("Track length: " + length + " mins");
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
