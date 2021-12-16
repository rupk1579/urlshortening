package com.online.assesment.urlshortening.repo;

import java.util.HashMap;
import java.util.Map;

public class ShortURLOriginalURLMappingRepo {

    private static Map<String,String> shortOriginalMap;
    private static Map<String,String> originalShortUrlMap;

    public ShortURLOriginalURLMappingRepo() {
        shortOriginalMap = new HashMap<>();
        originalShortUrlMap = new HashMap<>();
    }

    public Map<String, String> getShortOriginalMap() {
        return shortOriginalMap;
    }

    public Map<String, String> getOriginalShortUrlMap() {
        return originalShortUrlMap;
    }
}
