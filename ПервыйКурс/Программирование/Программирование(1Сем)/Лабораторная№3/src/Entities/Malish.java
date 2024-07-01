package Entities;

import Enums.Places;
import Interfaces.ForMalish;


public class Malish extends Entity implements ForMalish{

    public Malish(){
        name = "Малыш";
    }

    @Override
    public void sayPhrase(){
        System.out.print("\"Потому, что я хотел ехать один\"");


    }
    @Override
    public void wish(Karlson karlson, Places places){
        System.out.print("Он хочет отправиться с " + karlson + "ом");
        if(places == Places.CURRENTPLACE);
        {
            System.out.println(" на крышу");
        }
    }


    @Override
    public void ifNotAtHome() {
        System.out.println("Если " + this.name + "а " +  "нет дома,");
    }

    @Override
    public String excuse() {
        return this.name + " оправдается тем, что у него есть записка.";
    }

    @Override
    public void didntFear() {
        System.out.println( "что " + this.name + " не боялся.");
    }

    @Override
    public void flyWithFriend(Karlson karlson, Places places){
        System.out.print(super.name + " вылетел вместе с " + karlson +"ом" );
        if (places== Places.STARTINGPLACE){
            System.out.println(" из окна");
        }
    }

}
