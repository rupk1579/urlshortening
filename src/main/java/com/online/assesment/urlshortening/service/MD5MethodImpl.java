package com.online.assesment.urlshortening.service;

import com.online.assesment.urlshortening.repo.ShortURLOriginalURLMappingRepo;
import com.online.assesment.urlshortening.utils.MD5Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5MethodImpl implements IURLShorteningService {

    private ShortURLOriginalURLMappingRepo repo;

    public MD5MethodImpl(ShortURLOriginalURLMappingRepo repo) {
        this.repo = repo;
    }

    @Override
    public String convertToShortenURL(String originalUrl) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");//256-bit
        byte[] digest = messageDigest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
        String shortHexUrl = MD5Utils.bytesToHex(digest);
        System.out.println("shortHexUrl:" + shortHexUrl);
        return extract8charString(shortHexUrl, 8);
    }

    @Override
    public String getOriginalURL(String shortURL) {
        return null;
    }

    private String extract8charString(String hexString, int keyLength) {
        int start = 0;
        int sz = hexString.length();
        while (start <= sz - keyLength) {

            String extractStr = hexString.substring(start, start + keyLength - 1);
            if (!repo.getShortOriginalMap().containsKey(extractStr))
                return extractStr;
            start += 1;
        }
        return "";
    }
}
