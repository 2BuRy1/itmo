
package mymoves.drapion;

import Laba.Laba;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;

public class RockSlide extends PhysicalMove {

    public RockSlide(double pow, double acc) {


    }

    @Override
    protected String describe() {
        return "использует Rock Slide";
    }


    @Override
    public void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        if(Laba.chance(0.3)){
            Effect.flinch(p);
        }
    }
}


