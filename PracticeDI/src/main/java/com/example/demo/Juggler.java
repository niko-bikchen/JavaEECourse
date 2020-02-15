package com.example.demo;

public class Juggler implements Performer {

    private final int beanBags;

    public Juggler() {
        this(3);
    }

    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    @Override
    public void perform() {
        System.out.println("Juggling " + beanBags + " beanbags");
    }
}
