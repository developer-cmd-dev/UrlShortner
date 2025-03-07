package com.urlshortner.UrlShortner.Service;

import com.google.common.hash.Hashing;
import com.urlshortner.UrlShortner.Entity.UrlsData;
import com.urlshortner.UrlShortner.Exception.CustomException;
import com.urlshortner.UrlShortner.Repository.UrlDataRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UrlShortnerService {

    private HashMap<String,UrlsData> storage = new HashMap<>();


    private UrlDataRepository urlDataRepository;
    private RestTemplate restTemplate;

    public UrlShortnerService(UrlDataRepository urlDataRepository,RestTemplate restTemplate){
        this.urlDataRepository = urlDataRepository;
        this.restTemplate = restTemplate;
    }


    public UrlsData generateHash(String url) {
        UrlsData dbData = urlDataRepository.findByOriginalUrl(url).orElse(null);

        if(dbData ==null){
            String hashed = hashing(url);
            String regex = "(https?://[^/]+/)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String result = matcher.group();
                UrlsData data = UrlsData.builder().hashedUrl(result + hashed).hashcode(hashed).originalUrl(url).build();
                return urlDataRepository.save(data);
            }
        }
            return dbData;

    }

    public String redirecturl(String hashcode, HttpServletResponse response) {
        UrlsData data = storage.get(hashcode);
        try {
            return data.getOriginalUrl();
        } catch (Exception e) {
            throw new CustomException("Redirection Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public HashMap<String, UrlsData> getAll() {
        return storage;
    }


    private String hashing(String url) {
        return Hashing.sipHash24()
                .hashString(url, StandardCharsets.UTF_16).toString();

    }


}
