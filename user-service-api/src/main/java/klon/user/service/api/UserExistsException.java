package klon.user.service.api;

public class UserExistsException extends Exception{
    public UserExistsException(String userId) {
        super(String.format("User %s exists.", userId));
    }
}
