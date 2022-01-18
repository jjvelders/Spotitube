package nl.student.services.doa.entity;

public class PlaylistEntity {
    private int id;
    private String name;
    private int ownerId;

    public PlaylistEntity() {
    }

    public PlaylistEntity(int id, String name, int ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
