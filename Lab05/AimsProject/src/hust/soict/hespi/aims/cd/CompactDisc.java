package hust.soict.hespi.aims.cd;

import hust.soict.hespi.aims.disc.Disc;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.exception.PlayerException;

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

    public CompactDisc(String title, double cost) {
        this(0, title, null, (float) cost, null, "");  // Gọi constructor đầy đủ với id = 0, category = null, director = null, artist = ""
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
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            String errorMsg = "ERROR: Cannot play CD: " + getTitle() + " (No tracks or invalid length)";
            System.err.println(errorMsg);
            throw new PlayerException(errorMsg);
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("Artist: " + artist);
        System.out.println("CD length: " + getLength() + " mins");

        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                String trackErrorMsg = "ERROR: Cannot play track in CD " + getTitle() + ": " + e.getMessage();
                System.err.println(trackErrorMsg);
            }
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
