

package mypokemons;

import mymoves.SampleMove;
import mymoves.guzzlord.AirCutter;
import mymoves.guzzlord.AirSlash;
import mymoves.guzzlord.Confide;
import mymoves.guzzlord.LeafBlade;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;



public class guzzlord extends Pokemon {
public guzzlord(String name, int level) {
super(name, level);
setType(Type.DARK, Type.DRAGON);
setStats(223, 101, 53, 97, 53, 43);
setMove(new AirCutter(60, 95), new AirSlash(75, 95), new Confide(0, 0), new LeafBlade(90, 100));
}
}
