package nl.student.services.doa.Entity;

import java.util.Date;

public class UserEntity {
    private int OwnerId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String token;
    private Date tokenCreatedTime;

    public UserEntity() {
    }

    public UserEntity(int ownerId, String username, String password, String firstName, String lastName, String token, Date tokenCreatedTime) {
        OwnerId = ownerId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
        this.tokenCreatedTime = tokenCreatedTime;
    }

    public int getOwnerId() {
        return OwnerId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getToken() {
        return token;
    }

    public Date getTokenCreatedTime() {
        return tokenCreatedTime;
    }
}
