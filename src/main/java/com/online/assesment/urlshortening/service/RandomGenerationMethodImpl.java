package com.online.assesment.urlshortening.service;


import com.online.assesment.urlshortening.exception.ValidationException;
import com.online.assesment.urlshortening.repo.ShortURLOriginalURLMappingRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;

@Service
public class RandomGenerationMethodImpl implements IURLShorteningService {

    private String domain;

    private char requiredChar[];

    private int keyLength;

    private Random random;
    Map<String, String> originalShortMap;
    Map<String, String> shortOriginalMap;


    public RandomGenerationMethodImpl() {

        ShortURLOriginalURLMappingRepo shortURLOriginalURLMappingRepo = new ShortURLOriginalURLMappingRepo();

        originalShortMap = shortURLOriginalURLMappingRepo.getOriginalShortUrlMap();
        shortOriginalMap = shortURLOriginalURLMappingRepo.getShortOriginalMap();
        random = new Random();
        keyLength = 8;
        requiredChar = new char[62];
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {///(0-9)
                j = i + 48;
            } else if (i > 9 && i <= 35) {///(A-Z)
                j = i + 55;
            } else {//(a-z)
                j = i + 61;
            }
            requiredChar[i] = (char) j;
        }
        domain = "http://localhost";
    }

    @Override
    public String convertToShortenURL(String originalURL) {

        if(!validateUrl(originalURL)) throw  new ValidationException();
        String shortURL = "";

        originalURL = excractURL(originalURL);
        if (originalShortMap.containsKey(originalURL)) {
            shortURL = domain + "/" + originalShortMap.get(originalURL);
        } else {
            shortURL = domain + "/" + getShortKey(originalURL);
        }
        return shortURL;
    }

    @Override
    public String getOriginalURL(String shortURL) {
        String originalURL = "";
        String key = shortURL.substring(domain.length() + 1);
        originalURL = shortOriginalMap.get(key);
        return originalURL;
    }

    private boolean validateUrl(String url){
       if(StringUtils.isEmpty(url)) return false;
       return false;
    }

    private String excractURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        else if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    private String getShortKey(String originalURL) {

        String key = randomGenerateKey();
        shortOriginalMap.put(key, originalURL);
        originalShortMap.put(originalURL, key);
        return key;
    }

    private String randomGenerateKey() {

        while (true) {
            String key = "";
            for (int i = 0; i < keyLength; i++) {
                key += requiredChar[random.nextInt(62)];
            }
            if (!shortOriginalMap.containsKey(key)) {
                return key;
            }
        }

    }


}
