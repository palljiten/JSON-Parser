package com.jsonParser.jsonParser.exception;

import java.io.Serial;

public class JsonParserException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public JsonParserException(String message) {
        super(message);
    }

    public JsonParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParserException(Throwable cause) {
        super(cause);
    }
}