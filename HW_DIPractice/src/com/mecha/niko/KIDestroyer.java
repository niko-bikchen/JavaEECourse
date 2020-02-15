package com.mecha.niko;

public class KIDestroyer implements HeavyMecha, BattleMecha {
    private MechaPilot pilot;
    private EnergyShieldGenerator generator;
    private Gun bigGun;

    public KIDestroyer(MechaPilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public void shoot() {
        bigGun.shoot();
    }

    @Override
    public void reload() {
        bigGun.reload();
    }

    @Override
    public void activateBarrier() {
        generator.activateGenerator();
    }

    @Override
    public void heavyStomp() {
        System.out.println("KIDestroyer stomps.");
    }

    @Override
    public void walk() {
        System.out.println("KIDestroyer slowly walks.");
    }

    @Override
    public void turn() {
        System.out.println("KIDestroyer slowly turns.");
    }

    @Override
    public void stop() {
        System.out.println("KIDestroyer stops.");
    }

    @Override
    public void honk() {
        System.out.println("KIDestroyer makes a loud sound.");
    }

    @Override
    public void getPilotData() {
        System.out.println(pilot.toString());
    }

    public void setGenerator(EnergyShieldGenerator generator) {
        this.generator = generator;
    }

    public void setBigGun(Gun bigGun) {
        this.bigGun = bigGun;
    }
}
