
package mymoves.skorupi;

import Laba.Laba;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class PinMissile extends PhysicalMove {
    public PinMissile(double pow, double acc){
        super(Type.BUG, pow, acc);
        if (Laba.chance(6.0/8)) {
            if (Laba.chance(0.5)) hits = 2;
            else hits = 3;
        } else { if (Laba.chance(0.5)) hits = 4;
            else hits = 5;


        }
    }


    @Override
    protected String describe(){
        return "использует PinMissile" ;
    }
}
