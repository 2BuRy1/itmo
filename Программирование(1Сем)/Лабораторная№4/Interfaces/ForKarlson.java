package Interfaces;

import Entities.FlyException;
import Entities.Malish;
import Enums.Places;

public interface ForKarlson {



    void scream(Malish m);

    void readyForAFlight();

    void pressTheButton();

    void increaseHeight(Malish malish);
    void fly(Places places) throws FlyException;

}
