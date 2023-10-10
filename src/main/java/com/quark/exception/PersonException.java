package com.quark.exception;

import lombok.Data;

@Data
public class PersonException extends RuntimeException {

    private String note;

    public PersonException(String message, Throwable cause, String note) {
        super(message, cause);
        this.note = note;
    }
}
