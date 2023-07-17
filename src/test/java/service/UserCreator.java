package service;

import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

public class UserCreator {
    private static String name;
    private static String email;
    private static String password;
    public static String passwordRepeated;

    /**
     * Creates user with properties from bundle
     */
    public UserCreator(){
        name = UserPropertiesReader.getUserData("user.name");
        email = UserPropertiesReader.getUserData("user.email");
        password = UserPropertiesReader.getUserData("user.password");
        passwordRepeated = UserPropertiesReader.getUserData("user.password_repeated");
    }

    /**
     * Creates random user properties such as name, email, password
     * @param length        Choose length of name to be generated, must be more than 1
     */
    public UserCreator(int length){
        length = length > 1 ? length : length * length;
        name = RandomStringUtils.randomAlphanumeric(new Random().nextInt(length));
        email = RandomStringUtils.randomAlphanumeric(new Random().nextInt(6)) + "@epam.com";
        password = RandomStringUtils.randomAlphanumeric(new Random().nextInt(10));
        passwordRepeated = password;
    }


    public User createUser(){
        return new User(name, email, password, passwordRepeated);
    }
}
