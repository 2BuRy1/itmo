
import Entities.Engine;
import Entities.Entity;
import Entities.Karlson;
import Entities.Malish;
import Enums.Action;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;


public class Main {


    public static void main(String[] args) {
        Malish melkiy = new Malish();
        Karlson karlson = new Karlson();
        Engine engine = new Engine();
        story(melkiy, karlson, engine);
    }
public static void story(Malish melkiy, Karlson karlson, Engine engine){
        melkiy.SaySomething(Action.Say);
        System.out.println("-ответил " + melkiy.getName());
        System.out.println("Вот и теперь то же самое");
        melkiy.WishSomething(Action.Wish, karlson);
        System.out.println(melkiy.CurrentPlace());
        System.out.println("Поэтому лучше не просить разрешения");
    System.out.print(melkiy.isAtHome());
    System.out.println(melkiy.excuse());
    karlson.BeReadyForAFlight();
    karlson.BeReady(true);
    //karlson.PresssTheButton(Action.PressButton, karlson.BeReady(false), Action.Buzz, engine );
    if(!karlson.PresssTheButton(Action.PressButton, karlson.BeReady(true), Action.Buzz, engine)){
        return;}
    karlson.SaySomething(Action.Say);
    System.out.println("Мы сейчас взлетим!");
    System.out.print(melkiy.StartingPlace());
    System.out.print(karlson.StartingPlace());
    karlson.IncreaseHeight(Action.IncreaseHeght, melkiy);
    karlson.Fly(Action.Fly, engine);
    engine.Rattle(Action.Rattle);
    System.out.println(melkiy.Fear());










    }
}



