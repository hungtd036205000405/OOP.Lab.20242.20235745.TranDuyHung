package hust.soict.hespi.aims.cd;

import hust.soict.hespi.aims.disc.Disc;
import hust.soict.hespi.aims.media.Playable;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks;

    //  Constructor đầy đủ
    public CompactDisc(int id, String title, String category, float cost, String director, String artist) {
        super(id, title, category, cost, director, 0); // length = tổng của tracks
        this.artist = artist;
        this.tracks = new ArrayList<>();
    }

    // Getter cho artist
    public String getArtist() {
        return artist;
    }

    // Thêm bài hát/nếu chưa tồn tại
    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Added track: " + track.getTitle());
        } else {
            System.out.println("Track already exists: " + track.getTitle());
        }
    }
    //  Xoá bài hát (nếu tồn tại)
    public void removeTrack(Track track) {
        if (tracks.remove(track)) {
            System.out.println("Removed track: " + track.getTitle());
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }
    //  Độ dài CD là tổng độ dài bài hát
    @Override
    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }

    //  Phát CD nếu nó có độ dài hợp lệ
    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play CD: " + getTitle() + " (Length is 0 or less)");
            return;
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);
        System.out.println("CD length: " + getLength() + " mins");
        for (Track track : tracks) {
            track.play();
        }
    }
    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("CD - %s | Artist: %s | Director: %s | Total Length: %d mins\n",
                super.toString(), artist, getDirector(), getLength()));
        sb.append("Tracks:\n");
        for (Track t : tracks) {
            sb.append("  - ").append(t).append("\n");
        }
        return sb.toString();
    }
}
