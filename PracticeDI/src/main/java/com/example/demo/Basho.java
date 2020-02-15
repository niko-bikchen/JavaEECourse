package com.example.demo;

public class Basho implements Poem {

    private static String[] poemLines = {
            "Autumn moonlight",
            "a worm digs silently",
            "into the chestnut."
    };

    @Override
    public void recite() {
        for (String line : poemLines) {
            System.out.println(line);
        }
    }
}
