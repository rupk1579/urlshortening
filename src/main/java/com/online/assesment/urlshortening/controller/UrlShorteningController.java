package com.online.assesment.urlshortening.controller;


import com.online.assesment.urlshortening.service.IURLShorteningService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;


public class UrlShorteningController {

    private IURLShorteningService iurlShorteningService;

    public UrlShorteningController(IURLShorteningService iurlShorteningService) {
        this.iurlShorteningService = iurlShorteningService;
    }

    @PostMapping
    public String shortURL(@RequestParam String OriginalUrl) throws NoSuchAlgorithmException {
            return  iurlShorteningService.convertToShortenURL(OriginalUrl);
    }

    @PostMapping
    public String fetchOriginURL(@RequestParam String shortUrl){
            return  iurlShorteningService.getOriginalURL(shortUrl);
    }
}
