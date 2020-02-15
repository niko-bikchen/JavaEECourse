package com.mecha.niko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeployMechas {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        BattlePlatformOmega battlePlatform = (BattlePlatformOmega) context.getBean("omega-battle-platform");

        battlePlatform.shoot();
        battlePlatform.getPilotData();
        battlePlatform.heavyStomp();
    }
}
