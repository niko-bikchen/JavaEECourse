package com.example.demo;

public class Piano implements Instrument {
    public Piano() {

    }

    @Override
    public void play() {
        System.out.println("*piano sounds*");
    }
}
