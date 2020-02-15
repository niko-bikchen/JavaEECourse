package com.mecha.niko;

public class XZAlpha implements FastMecha, ScoutMecha {
    private MechaPilot pilot;
    private CamouflagingDevice camouflage;

    public XZAlpha(MechaPilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public void dodge() {
        System.out.println("XZAlpha makes a somersault and dodges the attack.");
    }

    @Override
    public void run() {
        System.out.println("XZAlpha start running really fast.");
    }

    @Override
    public void jump() {
        System.out.println("XZAlpha jumps really high.");
    }

    @Override
    public void camouflage() {
        System.out.println("XZAlpha activates its camouflage...");
        camouflage.activateCamouflage();
    }

    @Override
    public void decoy() {
        System.out.println("XZAlpha activates its decoy...");
        camouflage.activateDecoy();
    }

    @Override
    public void walk() {
        System.out.println("XZAlpha walks.");
    }

    @Override
    public void turn() {
        System.out.println("XZAlpha turns.");
    }

    @Override
    public void stop() {
        System.out.println("XZAlpha stops...");
    }

    @Override
    public void honk() {
        System.out.println("XZAlpha honks...");
    }

    @Override
    public void getPilotData() {
        System.out.println(pilot.toString());
    }

    public void setCamouflage(CamouflagingDevice camouflage) {
        this.camouflage = camouflage;
    }
}
