package com.carbonurlshorten.urlShortener.services;

import com.carbonurlshorten.urlShortener.constants.Status;
import com.carbonurlshorten.urlShortener.dto.ApplicationResponseDto;
import com.carbonurlshorten.urlShortener.dto.ShortUrlRequestDto;
import com.carbonurlshorten.urlShortener.models.PostUrls;
import com.carbonurlshorten.urlShortener.repository.PostUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class UrlShortenerService {

    @Value("${app.serverDomainPath}")
    private String extServicePath;

    private final PostUrlRepository postUrlRepository;
    private final BaseConverter baseConverter;

    private static Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);


    public UrlShortenerService(PostUrlRepository postUrlRepository, BaseConverter baseConverter) {
        this.postUrlRepository = postUrlRepository;
        this.baseConverter = baseConverter;
    }

    public ApplicationResponseDto convertToShortUrl(ShortUrlRequestDto req) throws Exception {
        logger.info("New full url request: "+req.getUrl());
        PostUrls url = new PostUrls();

        if (validateUrl(req.getUrl())){
            Optional<PostUrls> checkLink = postUrlRepository.findByFullUrl(req.getUrl().trim());
            if (!checkLink.isPresent()){

                url.setFullUrl(req.getUrl().trim());
                url.setCreatedAt(LocalDateTime.now());
                url = postUrlRepository.save(url);
            }else{

                url = checkLink.get();
            }

        }

        ApplicationResponseDto applicationResponseDto = ApplicationResponseDto.builder()
                .status(Status.SUCCESSFUL)
                .statusCode("00")
                .statusMessage("Post url shorten successfully")
                .shortUrl(extServicePath+baseConverter.encode(url.getId()))
                .build();

        return applicationResponseDto;

    }

    public String getFullPostUrlMain(String shortUrl) throws Exception {
        System.out.println("Short url: "+shortUrl);
        long postId = baseConverter.decode(shortUrl);
        PostUrls entity = postUrlRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("There is no post found with the provided url " + shortUrl));

        return entity.getFullUrl();
    }

    private boolean validateUrl(String postUrl) throws Exception {
        boolean status = true;
        if (Objects.isNull(postUrl) || postUrl.isEmpty()){
            throw new Exception("Post Url must be supplied");
        }
        if (!(postUrl.startsWith("http") || postUrl.startsWith("https"))){
            throw new Exception("A valid web post url must be supplied! ");
        }

        return status;
    }
}
