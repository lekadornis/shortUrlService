package com.carbonurlshorten.urlShortener.repository;

import com.carbonurlshorten.urlShortener.models.PostUrls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostUrlRepository extends JpaRepository<PostUrls, Long> {
    Optional<PostUrls> findByFullUrl(String url);
}
