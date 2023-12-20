

package mypokemons;

import mymoves.wigglybuff.WildCharge;
import ru.ifmo.se.pokemon.Type;

public class Wigglytuff extends Jigglypuff {
    public Wigglytuff(String name , int level){
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        setStats(140, 70, 45, 85 ,50, 45);
        addMove(new WildCharge(90,100));
    }
}
