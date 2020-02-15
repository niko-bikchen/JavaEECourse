package com.mecha.niko;

public class MechaPilot {
    private int age;
    private String name;
    private String rank;

    public MechaPilot(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void tellRank() {
        System.out.println("My rank is " + rank);
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Age: " + age + "; Rank: " + rank;
    }
}
