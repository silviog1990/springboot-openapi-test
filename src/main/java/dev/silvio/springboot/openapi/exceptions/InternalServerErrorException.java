package dev.silvio.springboot.openapi.exceptions;

public class InternalServerErrorException extends RuntimeException {
    String message;

    public InternalServerErrorException(String message) {
        super(message);
    }

}
