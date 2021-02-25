package com.carbonurlshorten.urlShortener.dto;

import com.carbonurlshorten.urlShortener.constants.ErrorCode;
import com.carbonurlshorten.urlShortener.constants.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponseDto {
    private Status status;
    private String statusCode;
    private ErrorCode errorType;
    private String statusMessage;
    private String errorMessage;
    private String shortUrl;
}
