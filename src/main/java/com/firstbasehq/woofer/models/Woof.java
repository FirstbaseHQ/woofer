package com.firstbasehq.woofer.models;

import lombok.Data;

@Data
public class Woof {
    private int id;
    private String message;
    private Dog dog;
}
