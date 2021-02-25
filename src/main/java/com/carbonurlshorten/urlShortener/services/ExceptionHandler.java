package com.carbonurlshorten.urlShortener.services;

import java.util.Objects;


public abstract class ExceptionHandler extends RuntimeException {
    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
        if (Objects.isNull(this.getCause()) && Objects.isNull(cause)) this.initCause(cause);
    }
}
