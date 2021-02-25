package com.carbonurlshorten.urlShortener.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "postshorturls")
@Data
public class PostUrls {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String fullUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
