package Entities;

import Enums.Action;
import Interfaces.ForMalish;
import Interfaces.Places;


public class Malish extends Entity implements ForMalish, Places{

    public Malish(){
        name = "Малыш";
    }




    public void SaySomething(Action action){
        if (action == Action.SAY){
            System.out.print("\"Потому, что я хотел ехать один\"");

        }
    }

    public void WishSomething(Action action){
        if (action == Action.WISH){
            System.out.print("Он хочет отправиться с ");
        }
    }
    public void WishSomething(Action action, Karlson b){
        WishSomething(action);
        System.out.print( b + "ом");
    }
    @Override public String isAtHome() {
        return "Если " + this.name + "а " +  "нет дома,";
    }

    @Override
    public String excuse() {
        return this.name + " оправдается тем, что у него есть записка.";
    }

    @Override
    public String Fear() {
        return "что " + this.name + " не боялся.";
    }

    @Override
    public String CurrentPlace() {
        return " на крышу";
    }

    @Override
    public String StartingPlace() {
        return this.name + " вылетел вместе с ";
    }

}
