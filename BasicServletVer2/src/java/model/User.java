package model;

public class User {

    private String userId;
    private String userName;
    private Integer password;
    private Integer role;

    public User() {
    }

    public User(String userId, String userName, Integer password, Integer role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getInformation() {
        return "User ID: " + userId + "<br>" + "Password: " + password + "<br>" + "Fullname: " + userName + "<br>" + "Role: " + role;
    }
}
