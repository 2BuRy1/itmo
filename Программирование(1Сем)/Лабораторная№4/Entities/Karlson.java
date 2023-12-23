package Entities;

import Enums.Places;
import Interfaces.ForEngine;
import Interfaces.ForKarlson;

;

public class Karlson extends Entity implements ForKarlson{

    public final Engine engine = new Engine();
    public Karlson() {
        name = "Карлсон";
    }
    @Override
    public void readyForAFlight() {
        System.out.println(  super.name + " был готов к полету");
    }

    public class Engine extends Entity implements ForEngine{
        private boolean WoundUp;
        public Engine() {
            name = "Мотор";

        }

        public void setBeWoundUp(boolean beWoundUp) {
            WoundUp = beWoundUp;
        }


        @Override
        public void testEngine() {
            System.out.println("чтобы испытать " + super.name);
        }

        public boolean isWoundup() {
            return WoundUp;

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


    @Override
    public void pressTheButton() {
        System.out.println(super.name + " нажал на кнопку ");
    }
    @Override
    public void scream(Malish m){
        System.out.println("Залезай ко мне на плечи\" - крикнул " + super.name);
    }
    @Override
    public void increaseHeight(Malish melkiy){
        System.out.println("так что " +super.name + " и " + melkiy + " набрали высоту");
    }


@Override
    public void fly(Places places) throws FlyException {
        if(!engine.isWoundup()){
            throw new FlyException(name);

        }
        else {
            System.out.print(super.name + " сделал круг ");
            if (places == Places.CURRENTPLACE){
                System.out.print("над крышей ");
            }
        }


    }

}





