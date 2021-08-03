package klon.user.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class MissingUserException extends RuntimeException {

    MissingUserException(String message) {
        super(message);
    }
}
