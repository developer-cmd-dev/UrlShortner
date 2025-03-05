package com.urlshortner.UrlShortner.Controller;


import com.urlshortner.UrlShortner.Entity.UrlsData;
import com.urlshortner.UrlShortner.Service.UrlShortnerService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/url-shortner")
public class UrlShortnerController {

    private UrlShortnerService urlShortnerService;

    public UrlShortnerController(UrlShortnerService urlShortnerService){
        this.urlShortnerService=urlShortnerService;
    }


    @GetMapping
    public String hashUrl(@RequestBody String url){

        String response = urlShortnerService.generateHash(url);
        String modifiedUrl = url.replaceAll("(?<=\\.com/).*", "");
        return modifiedUrl+response;
    }

    @GetMapping("/{hashed}")
    public void getUrl(@PathVariable String hashed, HttpServletResponse response){
        String url = urlShortnerService.getUrl(hashed);
        try{
        response.sendRedirect(url);
        } catch (Exception e) {
            log.info(e.toString());
        }
    }





}
