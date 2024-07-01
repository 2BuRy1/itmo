
package mymoves.guzzlord;

import Laba.Laba;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
public class AirSlash extends SpecialMove {
    public AirSlash(double pow, double acc){
        super(Type.FLYING, pow , acc);
    }

    @Override
    public void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        if(Laba.chance(0.3)){
            Effect.flinch(p);
        }
    }


    @Override
    protected String describe(){
        return "использует Air Slash" ;
    }
}
