package Laba;

import mypokemons.*;

import ru.ifmo.se.pokemon.Battle;
public class Laba {
//https://pokemondb.net/pokedex/guzzlord
//https://pokemondb.net/pokedex/skorupi
//https://pokemondb.net/pokedex/drapion
//https://pokemondb.net/pokedex/igglybuff
//https://pokemondb.net/pokedex/jigglypuff
//https://pokemondb.net/pokedex/wigglytuff
    public static void main(String[] args) {
        Battle fight = new Battle();
        fight.addAlly(new guzzlord ("Guzzy", 2));
        fight.addAlly(new Skorupi("Skorpi", 2));
        fight.addAlly(new Wigglytuff("Wistle", 1));
        fight.addFoe(new Drapion("Drappy", 3));
        fight.addFoe(new Jigglypuff("Jiggle", 1));
        fight.addFoe(new igglybuff("Iggle", 1));
        fight.go();
    }

    public static boolean chance(double d) {
        return d > Math.random();

    }
}

