package com.urlshortner.UrlShortner.Controller;
import com.urlshortner.UrlShortner.ApiResponse.UrlResponseBody;
import com.urlshortner.UrlShortner.Exception.CustomException;
import com.urlshortner.UrlShortner.Service.UrlShortnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/")
public class UrlShortnerController {

    private final UrlShortnerService urlShortnerService;

    public UrlShortnerController(UrlShortnerService urlShortnerService){
        this.urlShortnerService=urlShortnerService;
    }

    @PostMapping
    public ResponseEntity<UrlResponseBody> hashUrl(@RequestBody String url){
      try {
          UrlResponseBody response = urlShortnerService.generateUrl(url);
          return new ResponseEntity<>(response,HttpStatus.OK);
      }catch (Exception e){
          throw new CustomException("Something Went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

}
