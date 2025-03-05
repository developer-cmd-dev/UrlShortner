package com.urlshortner.UrlShortner.Service;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Slf4j
@Service
public class UrlShortnerService  {

    private HashMap<String,String> storage = new HashMap<>();

    public String generateHash(String url){
        if(!storage.containsValue(url)){
            String hashed=hashing(url);
            storage.put(hashed,url);
            return hashed;
        }
        return storage.get(url);
    }

    public String getUrl(String hashcode){
        return storage.get(hashcode);
    }





    private String hashing(String url){
        return Hashing.sipHash24()
                .hashString(url, StandardCharsets.UTF_16).toString();

    }



}
