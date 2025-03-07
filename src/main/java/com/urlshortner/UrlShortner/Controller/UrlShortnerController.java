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
      try {
          UrlsData response = urlShortnerService.generateHash(url);
          return new ResponseEntity<>(response,HttpStatus.OK);
      }catch (Exception e){
          throw new CustomException("Something Went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/{hashed}")
    public ResponseEntity<String> redirectUrl(@PathVariable String hashed, HttpServletResponse response){

        String url = urlShortnerService.redirecturl(hashed, response);
        if(url!=null) {
           return new ResponseEntity<>(url, HttpStatus.OK);
        }
            throw new CustomException("Provided Link is wrong.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-all")
    public ResponseEntity<HashMap<String,UrlsData>> getAll(){
        HashMap<String,UrlsData> response =  urlShortnerService.getAll();
        log.info(response.toString());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }







}
