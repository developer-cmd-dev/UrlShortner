package com.urlshortner.UrlShortner.Repository;

import com.urlshortner.UrlShortner.Entity.UrlsData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UrlDataRepository extends MongoRepository<UrlsData,String> {



}
