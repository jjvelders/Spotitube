package nl.student.services.domain.dto;

public class UserDTO {
    private String user;
    private String password;
    private String token;

    public UserDTO() {
    }

    public UserDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public UserDTO(String user, String password, String token) {
        this.user = user;
        this.password = password;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
