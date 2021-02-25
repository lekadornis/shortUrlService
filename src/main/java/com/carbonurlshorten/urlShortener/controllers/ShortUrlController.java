package com.carbonurlshorten.urlShortener.controllers;

import com.carbonurlshorten.urlShortener.dto.ApplicationResponseDto;
import com.carbonurlshorten.urlShortener.dto.ShortUrlRequestDto;
import com.carbonurlshorten.urlShortener.services.UrlShortenerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    private final UrlShortenerService urlShortenerService;

    public ShortUrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("getshorturl")
    public ResponseEntity<ApplicationResponseDto> convertToShortUrl(@RequestBody ShortUrlRequestDto request) throws Exception {
        return ResponseEntity.ok(urlShortenerService.convertToShortUrl(request));
    }


}
