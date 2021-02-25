package com.carbonurlshorten.urlShortener;

import com.carbonurlshorten.urlShortener.constants.Status;
import com.carbonurlshorten.urlShortener.controllers.ShortUrlController;
import com.carbonurlshorten.urlShortener.dto.ApplicationResponseDto;
import com.carbonurlshorten.urlShortener.dto.ShortUrlRequestDto;
import com.carbonurlshorten.urlShortener.services.UrlShortenerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShortUrlController.class)
public class UrlShortenerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    ShortUrlRequestDto testReq = new ShortUrlRequestDto("https://stackoverflow.com/questions/26515700/mysql-jdbc-driver-5-1-33-time-zone-issue");
    ApplicationResponseDto responseDto = ApplicationResponseDto.builder()
            .status(Status.SUCCESSFUL)
            .statusCode("00")
            .shortUrl("http://localhost:8080/b")
            .statusMessage("Post url shorten successfully").build();

    @Test
    private void checkShortUrlHandler() throws Exception{
        Mockito.when(urlShortenerService.convertToShortUrl(testReq)).thenReturn(responseDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getshorturl")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        MvcResult mockResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mockResult.getResponse());

        String expectedResult = "{\n" +
                "    \"status\": \"SUCCESSFUL\",\n" +
                "    \"statusCode\": \"00\",\n" +
                "    \"errorType\": null,\n" +
                "    \"statusMessage\": \"Post url shorten successfully\",\n" +
                "    \"errorMessage\": null,\n" +
                "    \"data\": \"http://localhost:8080/b\"\n" +
                "}";

        Assert.assertEquals(expectedResult, mockResult.getResponse());
        
    }

}
