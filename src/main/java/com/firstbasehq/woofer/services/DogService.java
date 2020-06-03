package com.firstbasehq.woofer.services;

import com.firstbasehq.woofer.models.Dog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Getter
@Setter
public class DogService {

    private List<Dog> dogs = new ArrayList<>();

    public Dog newPet(String name, String breed, String password, String pictureUrl) {
        Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setName(name);
        dog.setProfile_picture(pictureUrl);
        dog.setPassword(password);
        dog.setId(ThreadLocalRandom.current().nextInt());
        this.dogs.add(dog);
        return dog;
    }

    public Dog getDogById(int id) {
        return this.dogs.stream().filter(dog -> dog.getId() == id).findFirst().orElse(null);
    }
}
