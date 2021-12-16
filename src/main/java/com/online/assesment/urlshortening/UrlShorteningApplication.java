package com.online.assesment.urlshortening;

import com.online.assesment.urlshortening.controller.UrlShorteningController;
import com.online.assesment.urlshortening.service.IURLShorteningService;
import com.online.assesment.urlshortening.service.RandomGenerationMethodImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShorteningApplication {



	public static void main(String[] args) {
		SpringApplication.run(UrlShorteningApplication.class, args);



		IURLShorteningService iurlShorteningService =  new RandomGenerationMethodImpl();
		UrlShorteningController urlShorteningController = new UrlShorteningController(iurlShorteningService);

		//Test Case 1
		String shortUrl1 = urlShorteningController.shortURL("http://www.google.com");
		System.out.println("ShortUrl is:"+shortUrl1);



		String originalUrl = urlShorteningController.fetchOriginURL(shortUrl1);
		System.out.println("Original url is:"+originalUrl);

		//Test Case 2
		String shortUrl2 = urlShorteningController.shortURL("http://www.freecharge.in");
		System.out.println("ShortUrl is:"+shortUrl2);


		String originalUrl2 = urlShorteningController.fetchOriginURL(shortUrl2);
		System.out.println("Original url is:"+originalUrl);




	}

}
