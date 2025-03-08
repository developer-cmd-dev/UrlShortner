package com.urlshortner.UrlShortner.Repository;

import com.urlshortner.UrlShortner.ApiResponse.UrlResponseBody;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApiResponseRepository extends MongoRepository<UrlResponseBody,String> {
    Optional<UrlResponseBody> findByDestination (String url);
}
