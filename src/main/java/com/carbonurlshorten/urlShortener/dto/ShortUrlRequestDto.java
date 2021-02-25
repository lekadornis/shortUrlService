package com.carbonurlshorten.urlShortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShortUrlRequestDto {
    @NotNull(message = "Web url must be supplied")
    private String url;
}
