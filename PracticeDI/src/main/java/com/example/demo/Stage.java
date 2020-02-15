package com.example.demo;

public class Stage {
    private Stage() {
    }

    private static class StageSingletonHolder {
        static Stage instance = new Stage();
    }

    public static Stage getInstance() {
        return StageSingletonHolder.instance;
    }

    public void turnOnLights() {
        System.out.println("Turning the lights on");
    }

    public void turnOffLights() {
        System.out.println("Turning the lights off");
    }
}
