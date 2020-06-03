package com.firstbasehq.woofer.services;

import com.firstbasehq.woofer.models.Dog;
import com.firstbasehq.woofer.models.Woof;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class WoofService {
    public List<Woof> woofs = new ArrayList<>();

    public Woof newWoof(Dog dog, String message)
    {
        Woof woof = new Woof();
        woof.setDog(dog);
        woof.setId(ThreadLocalRandom.current().nextInt());
        woof.setMessage(message);
        this.woofs.add(woof);
        return woof;
    }
}
