
import Entities.Engine;
import Entities.Karlson;
import Entities.Malish;
import Enums.Action;


public class Main {


    public static void main(String[] args) {
        Malish melkiy = new Malish();
        Karlson karlson = new Karlson();
        Engine engine = new Engine();
        story(melkiy, karlson, engine);
    }
public static void story(Malish melkiy, Karlson karlson, Engine engine){
        melkiy.SaySomething(Action.SAY);
        System.out.println("-ответил " + melkiy.getName());
        System.out.println("Вот и теперь то же самое");
        melkiy.WishSomething(Action.WISH, karlson);
        System.out.println(melkiy.CurrentPlace());
        System.out.println("Поэтому лучше не просить разрешения");
    System.out.print(melkiy.isAtHome());
    System.out.println(melkiy.excuse());
    karlson.BeReadyForAFlight();
    karlson.BeReady(true);
    if(!karlson.PresssTheButton(Action.PRESSTHEBUTTON, karlson.BeReady(true), Action.BUZZ, engine)){
        return;}
    karlson.SaySomething(Action.SAY);
    System.out.println("Мы сейчас взлетим!");
    System.out.print(melkiy.StartingPlace());
    System.out.print(karlson.StartingPlace());
    karlson.IncreaseHeight(Action.INCREASEHEIGHT, melkiy);
    karlson.Fly(Action.FLY, engine);
    engine.Rattle(Action.RATTLE);
    System.out.println(melkiy.Fear());










    }
}



