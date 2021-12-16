package com.online.assesment.urlshortening.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Base62DecodingMethodImpl implements IURLShorteningService {

    private HashMap<String, Integer> ltos;
    private HashMap<Integer, String> stol;
    private String elements;
    private static int COUNTER;

    public Base62DecodingMethodImpl() {
        ltos = new HashMap<>();
        stol = new HashMap<>();
        COUNTER = 1000000000;
        elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    }

    @Override
    public String convertToShortenURL(String originalUrl) throws NoSuchAlgorithmException {
        String shortUrl = base10ToBase62(COUNTER);
        ltos.put(originalUrl, COUNTER);
        stol.put(COUNTER, originalUrl);
        COUNTER++;
        return "http://localhost:" + shortUrl;

    }

    @Override
    public String getOriginalURL(String shortURL) {

        shortURL = shortURL.substring("http://localhost/".length());
        int count = base62ToBase10(shortURL);
        return stol.get(count);
    }

    private int base62ToBase10(String shortUrl) {

        int n = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            n = n * 62 + convert(shortUrl.charAt(i));
        }
        return n;

    }

    private int convert(char c) {
        return elements.indexOf(c);
    }

    private String base10ToBase62(int n) {

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 8) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

}
