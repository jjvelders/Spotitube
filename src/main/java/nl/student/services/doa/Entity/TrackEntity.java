package nl.student.services.doa.Entity;

import java.util.Date;

public class TrackEntity {
    private int id;
    private final String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private Date publicationDate = null;
    private String description = null;
    private boolean offlineAvailable;

    public TrackEntity(int id, String title, String performer, int duration, String album, int playcount, Date publicationDate, String description, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsOfflineAvailable() {
        return offlineAvailable;
    }
}
