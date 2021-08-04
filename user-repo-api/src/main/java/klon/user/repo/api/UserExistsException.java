package klon.user.repo.api;

public class UserExistsException extends Exception {

    private static final long serialVersionUID = -4504201829999526669L;

    public UserExistsException(String userId) {
        super(String.format("User %s exists.", userId));
    }
}
