package com.firstbasehq.woofer.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogApiService {

    @Data
    static class DogResponse {
        private String message;
        private String status;
    }

    public static String getDogUrl() {
        return new RestTemplate().getForObject("https://dog.ceo/api/breeds/image/random", DogResponse.class).message;
    }

    public static String getBreedFromUrl(String url) {
        return url.split("/")[4];
    }
}
