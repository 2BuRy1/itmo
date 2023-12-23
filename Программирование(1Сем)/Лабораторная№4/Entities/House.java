package Entities;

import Interfaces.Visible;

public class House extends Entity{
private final String machinesName= "паровые машины";
private final String picturesName="картины с изображением петухов";
private final String elseNames = "все остальное";

public House(){
    name = "домик";

    }
    public class SteamEngines implements Visible {

        @Override
        public void wishToSee(Malish malish) {
            System.out.println(malish + " хотел увидеть " + machinesName);

        }
    }
    public class Picture implements Visible{


        @Override
        public void wishToSee(Malish malish) {
            System.out.println(malish + " хотел увидеть " + picturesName);
        }
    }
    public class EverythingElse implements Visible{


        @Override
        public void wishToSee(Malish malish) {
            System.out.println(malish + " хотел увидеть " + elseNames);

        }
    }
}
