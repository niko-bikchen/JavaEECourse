package com.mecha.niko;

import java.util.Collection;

public class BattlePlatformOmega implements HeavyMecha, BattleMecha {
    private MechaPilot pilot;
    private Collection<Gun> manyGunz;
    private EnergyShieldGenerator generator;

    public BattlePlatformOmega(MechaPilot pilot, EnergyShieldGenerator generator) {
        this.pilot = pilot;
        this.generator = generator;
    }

    @Override
    public void shoot() {
        for (Gun gun : manyGunz) {
            gun.shoot();
        }
    }

    @Override
    public void reload() {
        for (Gun gun : manyGunz) {
            gun.reload();
        }
    }

    @Override
    public void activateBarrier() {
        generator.activateGenerator();
    }

    @Override
    public void heavyStomp() {
        System.out.println("Earth trembles as the Battle Platform Omega stomps.");
    }

    @Override
    public void walk() {
        System.out.println("Each step of Battle Platform Omega makes causes a little earthquake.");
    }

    @Override
    public void turn() {
        System.out.println("Battle Platform Omega turns.");
    }

    @Override
    public void stop() {
        System.out.println("Battle Platform Omega stops.");
    }

    @Override
    public void honk() {
        System.out.println("Battle Platform Omega makes a very loud sound.");
    }

    @Override
    public void getPilotData() {
        System.out.println(pilot.toString());
    }

    public void setManyGunz(Collection<Gun> manyGunz) {
        this.manyGunz = manyGunz;
    }
}
