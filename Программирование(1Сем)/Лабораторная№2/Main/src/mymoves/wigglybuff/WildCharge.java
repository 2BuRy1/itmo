
package mymoves.wigglybuff;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class WildCharge extends PhysicalMove {
    public WildCharge(double pow, double acc){
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applySelfDamage(Pokemon p, double v) {
        super.applySelfDamage(p, p.getStat(Stat.ATTACK)*0.25);
    }

    @Override
    protected String describe(){
        return "использует WildCharge" ;
    }
}
