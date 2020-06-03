package com.firstbasehq.woofer.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firstbasehq.woofer.models.Dog;
import com.firstbasehq.woofer.models.Woof;
import com.firstbasehq.woofer.services.DogApiService;
import com.firstbasehq.woofer.services.DogService;
import com.firstbasehq.woofer.services.WoofService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotsTask {

    @Autowired
    private DogService dogService;

    @Autowired
    private WoofService woofService;

    @Autowired
    private SimpMessagingTemplate template;

    private final DogApiService dogApiService;

    public BotsTask(DogApiService dogApiService) {
        this.dogApiService = dogApiService;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class RandomuserAPIResult {
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Login {
            private String password;
        }
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Name {
            private String first;
            private String last;
        }
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Result {
            private Login login;
            private Name name;
        }

        private List<Result> results;

        public RandomuserAPIResult() {
            results = new ArrayList<>();
        }
    }



    @Scheduled(fixedRate = 30000)
    public void genNewPet() {
        String pictureUrl = DogApiService.getDogUrl();
        RandomuserAPIResult result = new RestTemplate().getForObject("https://randomuser.me/api/", RandomuserAPIResult.class);
        this.dogService.newPet(result.results.get(0).name.first + result.results.get(0).name.last, DogApiService.getBreedFromUrl(pictureUrl), result.results.get(0).login.getPassword(), pictureUrl);
    }

    @Scheduled(fixedRate = 30000)
    public void genWoof()
    {
        List<Dog> dogs = this.dogService.getDogs();

        for (int i = 0; i < dogs.size(); ++i) {
            Woof woof = this.woofService.newWoof(dogs.get(i), new RestTemplate().getForObject("https://baconipsum.com/api/?type=meat&paras=1&format=text", String.class));
            this.template.convertAndSend("/topic/woofs", woof);
            woof.setDog(null);
            dogs.get(i).setWoofs(List.of(woof));
        }
    }
}
