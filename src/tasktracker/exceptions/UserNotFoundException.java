package tasktracker.exceptions;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
