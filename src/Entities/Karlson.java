package Entities;

import Enums.Action;
import Interfaces.ForKarlson;
import Interfaces.Places;
;

public class Karlson extends Entity implements ForKarlson, Places {

    public Karlson() {
        name = "Карлсон";
    }

    @Override
    public boolean BeReady(boolean Ready) {
        return Ready;
    }

    public void BeReadyForAFlight() {

        System.out.println(  super.name + " был готов к полету");
    }


    public boolean PresssTheButton(Action action, boolean Ready, Action act, Engine engine) {
        if (action == Action.PressButton && BeReady(Ready) && act==Action.Buzz) {

            System.out.print(super.name + " нажал на кнопку на животе,");
            engine.Buzzing(Action.Buzz);
            return true;
        }
        else{
                System.out.println("Карлсон не готов летать :(");
                return false;
        }
            }

public void SaySomething(Action action){
        if (action == Action.Say){
            System.out.println("\"Залезай ко мне на плечи\" - крикнул " + super.name);
        }
}


    @Override
    public String CurrentPlace() {
        return " над ближайшей крышей ";
    }

    @Override
    public String StartingPlace() {
        return super.name +"ом" + " из окна";
    }

    public void IncreaseHeight(Action action, Malish melkiy){
        if (action == Action.IncreaseHeght){
            System.out.println(" ,так что " +super.name + " и " + melkiy + " набрали высоту");


        }
    }
    public void Fly(Action action, Engine engine){
        if (action == Action.Fly){
            System.out.println( super.name + " сделал круг"  + CurrentPlace() + engine.testEngine());
        }
    }
}





