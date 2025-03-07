package com.urlshortner.UrlShortner.Repository;

import com.urlshortner.UrlShortner.Entity.UrlsData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UrlDataRepository extends MongoRepository<UrlsData,String> {


    Optional<UrlsData> findByOriginalUrl(String url);
}
