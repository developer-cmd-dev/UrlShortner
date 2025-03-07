package com.urlshortner.UrlShortner.Controller;


import com.urlshortner.UrlShortner.Entity.UrlsData;
import com.urlshortner.UrlShortner.Exception.CustomException;
import com.urlshortner.UrlShortner.Service.UrlShortnerService;
import com.urlshortner.UrlShortner.Utility.Hashing;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/url-shortner")
public class UrlShortnerController {

    private final UrlShortnerService urlShortnerService;

    public UrlShortnerController(UrlShortnerService urlShortnerService){
        this.urlShortnerService=urlShortnerService;
    }


    @PostMapping
    public ResponseEntity<UrlsData> hashUrl(@RequestBody String url){

        String response = urlShortnerService.generateHash(url);
        String regex="(https?://[^/]+/)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if(matcher.find() && response!=null){
            String result = matcher.group();
            return new ResponseEntity<>(UrlsData.builder().hashedUrl(result+response).hashcode(response).build(),
                    HttpStatus.OK);
        }
        throw new CustomException("Short url not generated!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{hashed}")
    public void getUrl(@PathVariable String hashed, HttpServletResponse response){
        String url = urlShortnerService.getUrl(hashed);
        log.info(url);
git
        if(url !=null ){
            try{
                response.sendRedirect(url);
            }catch (Exception e){
                throw new CustomException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

            throw new CustomException("Provided Link is wrong.", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/get-all")
    public ResponseEntity<HashMap<String,String>> getAll(){
        HashMap<String,String> response =  urlShortnerService.getAll();
        log.info(response.toString());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }







}
