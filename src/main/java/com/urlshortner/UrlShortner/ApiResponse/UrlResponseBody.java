package com.urlshortner.UrlShortner.ApiResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Document(collection = "url-api-data")
public class UrlResponseBody {
    @Id
    private String id;
    private String title;
    private String slashtag;
    private String destination;
    private String createdAt;
    private String updatedAt;
    private String expiredAt;
    private String status;
    private List<String> tags;
    private String linkType;
    private int clicks;
    private boolean isPublic;
    private String shortUrl;
    private String domainId;
    private String domainName;
    private Domain domain;
    private boolean https;
    private boolean favourite;
    private Creator creator;
    private boolean integrated;

    // Getters and Setters
    // (Omitted for brevity but should be generated)
}

@Getter
@Setter
@Builder
class Domain {
    private String id;
    private String ref;
    private String fullName;
    private Sharing sharing;
    private boolean revoked;
    private boolean active;

    // Getters and Setters
}

@Getter
@Setter
@Builder
class Sharing {
    private Protocol protocol;

    // Getters and Setters
}

@Getter
@Setter
@Builder
class Protocol {
    private List<String> allowed;
    private String defaultProtocol;

    // Getters and Setters
}

@Getter
@Setter
@Builder
class Creator {
    private String id;
    private String fullName;
    private String avatarUrl;
    // Getters and Setters
}
