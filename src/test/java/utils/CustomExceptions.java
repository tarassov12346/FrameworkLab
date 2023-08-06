package utils;

public class CustomExceptions {
    public static class UserAlreadyRegisteredException extends RuntimeException {
        public UserAlreadyRegisteredException(String message) {
            super(message);
        }
    }

    public static class InvalidNameException extends RuntimeException {
        public InvalidNameException(String message) { super(message) ;}
    }

    public static class PasswordMismatchException extends RuntimeException {
        public PasswordMismatchException(String message) { super(message); }
    }

    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) { super(message); }
    }
}
