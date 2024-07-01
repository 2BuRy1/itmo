

package mypokemons;

import mymoves.drapion.RockSlide;
import ru.ifmo.se.pokemon.Type;

public class Drapion extends Skorupi {

    public Drapion(String name, int level){
        super(name, level);
        setType(Type.POISON, Type.DARK);
        setStats(70, 90, 110, 60, 75, 95);
        addMove(new RockSlide(75, 90));

    }
}
