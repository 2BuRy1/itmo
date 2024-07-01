

package mypokemons;

import mymoves.igglybuff.DreamEater;
import mymoves.igglybuff.Psychic;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class igglybuff extends Pokemon {
    public igglybuff(String name, int level){
        super(name , level);
        setType(Type.NORMAL, Type.FAIRY);
        setStats(90, 30 ,15, 40, 20, 15);
        setMove(new DreamEater(100, 100), new Psychic(90, 100));

    }
}
