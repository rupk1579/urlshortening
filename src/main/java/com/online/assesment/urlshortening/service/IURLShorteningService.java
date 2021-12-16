package com.online.assesment.urlshortening.service;

import java.security.NoSuchAlgorithmException;

public interface IURLShorteningService {

    String convertToShortenURL(String originalUrl) throws NoSuchAlgorithmException;

    String getOriginalURL(String shortURL);
}
