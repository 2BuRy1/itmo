package Entities;

import Enums.Places;
import Interfaces.ForKarlson;

;

public class Karlson extends Entity implements ForKarlson{

    public Karlson() {
        name = "Карлсон";
    }
    @Override
    public void readyForAFlight() {
        System.out.println(  super.name + " был готов к полету");
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
    public void fly(Places places) {
        System.out.print(super.name + " сделал круг ");
        if (places == Places.CURRENTPLACE){
            System.out.print("над крышей ");
        }

    }

}





