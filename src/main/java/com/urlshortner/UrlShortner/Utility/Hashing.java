package com.urlshortner.UrlShortner.Utility;

public interface Hashing {
    default String hashString(String data){
        return "hashed";
    }
}
