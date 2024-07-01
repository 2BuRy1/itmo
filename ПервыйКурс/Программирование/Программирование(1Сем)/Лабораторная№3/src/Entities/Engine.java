package Entities;

import Interfaces.ForEngine;

public class Engine extends Entity implements ForEngine {

    public Engine() {
        name = "мотор";
    }

    @Override
    public void testEngine() {
        System.out.println("чтобы испытать " + super.name);
    }


    @Override
    public void buzz() {
        System.out.println(super.name + " загудел");

    }

    @Override
    public void rattle() {
        System.out.println(super.name + " тарахтел " + smoothAndReliable());
    }
}
