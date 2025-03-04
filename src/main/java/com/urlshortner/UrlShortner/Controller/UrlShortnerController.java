package com.urlshortner.UrlShortner.Controller;


import com.urlshortner.UrlShortner.Entity.UrlsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/url-shortner")
public class UrlShortnerController {


    @GetMapping
    public void getUrl(@RequestBody String url){
        log.info(url);
    }





}
