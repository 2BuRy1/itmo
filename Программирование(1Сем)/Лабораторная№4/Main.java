
import Entities.*;
import Enums.Places;
import Interfaces.Nailable;

public class Main {


    public static void main(String... args){
        Malish melkiy = new Malish();
        House house = new House();
        Karlson karlson = new Karlson();

        House.SteamEngines steamEngines = house.new SteamEngines();
        House.EverythingElse everythingElse = house.new EverythingElse();
        House.Picture picture = house.new Picture();

        melkiy.sayPhrase();
        System.out.println("-ответил " + melkiy);
        System.out.println("Вот и теперь то же самое");
        try {
            melkiy.wish(null, Places.CURRENTPLACE );
        }
        catch (NullPersonException e){
            System.out.println(e.getMessage());
            System.out.println(melkiy + " нашел друга - %s".formatted(karlson));
            melkiy.wish(karlson, Places.CURRENTPLACE);
        }
        System.out.println("Поэтому лучше не просить разрешения");
        melkiy.ifNotAtHome();
        System.out.println(melkiy.excuse());
        karlson.readyForAFlight();
        karlson.pressTheButton();
        karlson.engine.buzz();
        karlson.scream(melkiy);
        System.out.println("Мы сейчас взлетим!");
        karlson.engine.setBeWoundUp(false);
        try{
            karlson.fly(Places.CURRENTPLACE);
        }
        catch (FlyException e){
            System.out.println(e.getMessage());
            karlson.engine.setBeWoundUp(true);
            System.out.println(karlson.engine + " завелся");
        }
        melkiy.flyWithFriend(karlson, Places.STARTINGPLACE);
        karlson.increaseHeight(melkiy);
        karlson.engine.testEngine();
        karlson.engine.rattle();
        melkiy.didntFear();
        melkiy.see(house);
        melkiy.wishToEnter(house);
        steamEngines.wishToSee(melkiy);
        picture.wishToSee(melkiy);
        everythingElse.wishToSee(melkiy);
        Nailable tablet = new Nailable() {

            @Override
            public void nail(House house) {
                System.out.println("Табличка была прибита к " + house + " чтобы все знали, кто в нём живёт.");
            }
        };
        tablet.nail(house);

    }
}





