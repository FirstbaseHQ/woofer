package com.firstbasehq.woofer.services;


import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class DogApiServiceTest {

    @Test
    public void TestURLExtractor() {
        String breed = DogApiService.getBreedFromUrl("https://something/other/breed/test-breed");
        Assert.isTrue(breed.equals("test-breed"));
    }
}
