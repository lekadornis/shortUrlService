package com.carbonurlshorten.urlShortener.services;

import com.carbonurlshorten.urlShortener.constants.ErrorCode;
import com.carbonurlshorten.urlShortener.constants.Status;
import com.carbonurlshorten.urlShortener.dto.ApplicationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationResponseDto> handleInternalError
            (HttpServletRequest request, Exception e) {
        ApplicationResponseDto response = ApplicationResponseDto.builder()
                .status(Status.FAILED)
                .errorType(ErrorCode.PROCESSING)
                .statusCode("09")
                .statusMessage("Failed")
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
