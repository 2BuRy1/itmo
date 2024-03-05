package Entities;

import Enums.Places;
import Interfaces.ForMalish;
import Interfaces.Prettible;
import Interfaces.Smallable;


public class Malish extends Entity implements ForMalish {

    public Malish() {
        name = "Малыш";
    }

    @Override
    public void sayPhrase() {
        System.out.print("\"Потому, что я хотел ехать один\"");


    }

    @Override
    public void wish(Karlson karlson, Places places) throws NullPersonException {
        if(karlson == null){
            throw new NullPersonException(name);
        }
        else {
            System.out.print("Он хочет отправиться с " + karlson + "ом");
            if (places == Places.CURRENTPLACE) ;
            {
                System.out.println(" на крышу");
            }
        }
    }


    @Override
    public void ifNotAtHome() {
        System.out.println("Если " + this.name + "а " + "нет дома,");
    }

    @Override
    public String excuse() {
        return this.name + " оправдается тем, что у него есть записка.";
    }

    @Override
    public void didntFear() {
        System.out.println("что " + this.name + " не боялся.");
    }

    @Override
    public void flyWithFriend(Karlson karlson, Places places) {
        System.out.print(super.name + " вылетел вместе с " + karlson + "ом");
        if (places == Places.STARTINGPLACE) {
            System.out.println(" из окна");
        }
    }

    @Override
    public void see(House house) {
        class Shutters extends Entity implements Prettible {

            private Shutters() {
                name = "ставенками";
            }

            @Override
            public void cute() {
                System.out.print(" c красивыми " + new Shutters().getName() );

            }
        }
        class Porch extends Entity implements Smallable {

            private Porch(){
            name = "крылечко";
            }

            @Override
            public void beSmall() {
                System.out.println(" с маленьким " + name + "м");

            }
        }
        Shutters shutters = new Shutters();
        Porch porch = new Porch();
        System.out.print(name + " увидел " + house);
        shutters.cute();
        System.out.print(" и");
        porch.beSmall();
        }

    @Override
    public void wishToEnter(House house) {
        System.out.println(name + " хотел войти в " + house);
    }

}