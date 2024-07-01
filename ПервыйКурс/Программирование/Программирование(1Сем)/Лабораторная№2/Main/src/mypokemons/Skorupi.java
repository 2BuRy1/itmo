

package mypokemons;

import mymoves.skorupi.Leer;
import mymoves.skorupi.PinMissile;
import mymoves.skorupi.ShadowBall;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;


public class Skorupi extends Pokemon {
    public Skorupi(String name, int level){
        super(name, level);
        setType(Type.POISON, Type.BUG);
        setStats(40, 50, 90, 30, 55 ,65);
        setMove( new Leer(0, 100), new PinMissile(25, 95), new ShadowBall(80, 100) );

    }
}
