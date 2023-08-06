package service;

import com.github.javafaker.Faker;
import model.User;

public class UserCreator {
    private static String name;
    private static String email;
    private static String password;
    private static String passwordRepeated;

    private Faker faker;

    /**
     * Creates user properties such as name, email, password
     *
     * @param fromBundle True for user from bundle, false for user random user
     */
    public UserCreator(boolean fromBundle) {
        if (fromBundle) {
            name = UserPropertiesReader.getUserData("user.name");
            email = UserPropertiesReader.getUserData("user.email");
            password = UserPropertiesReader.getUserData("user.password");
            passwordRepeated = UserPropertiesReader.getUserData("user.password_repeated");
        } else {
            faker = new Faker();
            name = faker.name().username();
            email = faker.internet().emailAddress();
            password = faker.internet().password();
            passwordRepeated = password;
        }
    }

    public User createUser() {
        return new User(name, email, password, passwordRepeated);
    }

    public User createUserWithDifferentPasswords() {
        return new User(name, email, password, faker.internet().password());
    }

    public User createUserWithBlankName() {
        return new User("", email, password, passwordRepeated);
    }

    public User creteUserWithInvalidEmail() {
        return new User(name, "invalidEmail.com", password, passwordRepeated);
    }
}
