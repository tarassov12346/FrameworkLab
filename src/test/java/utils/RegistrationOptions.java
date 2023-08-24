package utils;

public enum RegistrationOptions {
    NAME("With blank name"),
    EMAIL("With invalid email"),
    PASSWORD("With different passwords"),
    DEFAULT("Valid user");

    RegistrationOptions(String property) {
    }
}
