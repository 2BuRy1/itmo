
import Entities.Engine;
import Entities.Karlson;
import Entities.Malish;
import Enums.Places;
public class Main {


    public static void main(String... args) {
        Malish melkiy = new Malish();
        Karlson karlson = new Karlson();
        Engine engine = new Engine();
        System.out.println("123"=="124");
        System.out.println(("12"+"3").intern()==("123"));
        story(melkiy, karlson, engine);
    }
    public static void story(Malish melkiy, Karlson karlson, Engine engine) {
        melkiy.sayPhrase();
        System.out.println("-ответил " + melkiy.getName());
        System.out.println("Вот и теперь то же самое");
        melkiy.wish(karlson, Places.CURRENTPLACE);
        System.out.println("Поэтому лучше не просить разрешения");
        melkiy.ifNotAtHome();
        System.out.println(melkiy.excuse());
        karlson.readyForAFlight();
        karlson.pressTheButton();
        engine.buzz();
        karlson.scream(melkiy);
        System.out.println("Мы сейчас взлетим!");
        melkiy.flyWithFriend(karlson, Places.STARTINGPLACE);
        karlson.increaseHeight(melkiy);
        karlson.fly(Places.CURRENTPLACE);
        engine.testEngine();
        engine.rattle();
        melkiy.didntFear();
    }










    }




