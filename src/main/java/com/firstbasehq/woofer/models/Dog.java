package com.firstbasehq.woofer.models;

import lombok.Data;

import java.util.List;

@Data
public class Dog {
    private int Id;
    private String name;
    private String breed;
    private List<Woof> woofs;
    private String profile_picture;
    private String password;
}
