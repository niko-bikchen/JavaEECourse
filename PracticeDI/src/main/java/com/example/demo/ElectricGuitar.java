package com.example.demo;

public class ElectricGuitar implements Instrument {
    public ElectricGuitar() {
    }

    @Override
    public void play() {
        System.out.println("*electric guitar sounds*");
    }
}
