package model;

import java.util.Objects;

/**
 * User model implementation
 * @param name                  User name
 * @param email                 User email
 * @param password              User password
 * @param passwordRepeated      User password to be repeated
 */
public record User(String name, String email, String password, String passwordRepeated) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(passwordRepeated, user.passwordRepeated);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordRepeated='" + passwordRepeated + '\'' +
                '}';
    }
}
