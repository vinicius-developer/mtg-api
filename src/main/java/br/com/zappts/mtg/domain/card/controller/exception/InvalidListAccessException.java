package br.com.zappts.mtg.domain.card.controller.exception;

public class InvalidListAccessException extends RuntimeException {

    public InvalidListAccessException(String message) {
        super(message);
    }

}
