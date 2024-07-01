package Interfaces;

import Entities.House;
import Entities.Karlson;
import Entities.Malish;
import Enums.Places;

public interface  ForMalish{
    void ifNotAtHome();
    String excuse();
    void didntFear();
    void wish(Karlson karlson, Places places);
    void sayPhrase();
    void flyWithFriend(Karlson karlson , Places places);
    void see(House house);
    void wishToEnter(House house);
}










