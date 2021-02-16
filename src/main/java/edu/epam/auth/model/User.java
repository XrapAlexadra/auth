package edu.epam.auth.model;

import java.time.LocalDate;

public class User extends Entity{

    private long id;
    private String login;
    private String email;
    private Role role;
    private UserStatus status;
    private String image;
    private String activationKey;
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;

    public User() {
    }

    public User(long id, String login, String email, Role role, UserStatus status) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public User setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public String getImage() {
        return image;
    }

    public User setImage(String image) {
        this.image = image;
        return this;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public User setActivationKey(String activationKey) {
        this.activationKey = activationKey;
        return this;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public User setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public User setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (login.equals(user.login) &&
                email.equals(user.email) &&
                role == user.role &&
                status == user.status &&
                registrationDate.equals(user.registrationDate));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = login.hashCode();
        result = prime * result + email.hashCode();
        result = prime * result + role.hashCode();
        result = prime * result + status.hashCode();
        result = prime * result + registrationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", activationKey='" + activationKey + '\'' +
                ", registrationDate=" + registrationDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
}
