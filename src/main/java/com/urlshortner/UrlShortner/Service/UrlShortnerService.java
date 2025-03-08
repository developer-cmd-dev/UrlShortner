package com.urlshortner.UrlShortner.Service;

import com.google.common.hash.Hashing;
import com.urlshortner.UrlShortner.ApiResponse.UrlResponseBody;
import com.urlshortner.UrlShortner.Entity.UrlsData;
import com.urlshortner.UrlShortner.Exception.CustomException;
import com.urlshortner.UrlShortner.Repository.ApiResponseRepository;
import com.urlshortner.UrlShortner.Repository.UrlDataRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UrlShortnerService {

    private HashMap<String,UrlsData> storage = new HashMap<>();


    private final UrlDataRepository urlDataRepository;
    private final ApiResponseRepository apiResponseRepository;
    private final RestTemplate restTemplate;
    @Value("${api.url}")
    private String uri;
    @Value("${api.key}")
    private  String apiKey;

    public UrlShortnerService(UrlDataRepository urlDataRepository,RestTemplate restTemplate,ApiResponseRepository apiResponseRepository){
        this.urlDataRepository = urlDataRepository;
        this.restTemplate = restTemplate;
        this.apiResponseRepository =apiResponseRepository;
    }




    public UrlResponseBody generateUrl(String url){
        UrlResponseBody data = apiResponseRepository.findByDestination(url).orElse(null);
        if(data==null){

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("apiKey",apiKey);
            UrlResponseBody urlRequestBody = UrlResponseBody.builder().destination(url).build();
            HttpEntity<UrlResponseBody> requestEntity= new HttpEntity<>(urlRequestBody,headers);
            try {
                ResponseEntity<UrlResponseBody> response = restTemplate.exchange(uri, HttpMethod.POST,requestEntity, UrlResponseBody.class);
               return apiResponseRepository.save(Objects.requireNonNull(response.getBody()));
            } catch (RuntimeException e) {
                log.info(e.getLocalizedMessage());
                throw new CustomException("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    return data;

    }



}
