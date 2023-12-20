
package mymoves.guzzlord;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class LeafBlade extends PhysicalMove {
    public LeafBlade(double pow, double acc) {
        super(Type.GRASS, pow, acc);

    }


    @Override
    public double calcCriticalHit(Pokemon att, Pokemon def) {
        if (att.getStat(Stat.SPEED) / (512.0 / 3) > Math.random()) {
            System.out.println("Критический удар!!");
            return 2.0;
        } else {
            return 1.0;
        }

    }


    @Override
    protected String describe(){
        return "использует LeafBlade" ;
    }
}
