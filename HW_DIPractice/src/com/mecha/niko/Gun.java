package com.mecha.niko;

public class Gun {
    private String name;

    public Gun(String name) {
        this.name = name;
    }

    public void shoot() {
        System.out.println(name + " makes " + "*" + name + " shooting sound*");
    }

    public void reload() {
        System.out.println(name + " reloads");
    }
}
