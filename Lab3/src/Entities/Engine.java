package Entities;

import Enums.Action;
import Interfaces.ForEngine;

public class Engine extends Entity implements ForEngine {


public Engine(){
    name="мотор";
}


public void Buzzing(Action action){
    if (action == Action.BUZZ){
        System.out.println(" и " + name +  " загудел");
    }
}


    @Override
    public String testEngine() {
        return " ,чтобы испытать " + super.name;
    }

    @Override
    public String adjectives() {
        return "ровно и надежно, ";
    }



    public void Rattle(Action action){
    if (action == Action.RATTLE) {
        System.out.println(super.name + " тарахтел " + adjectives());
    }
    }
}
