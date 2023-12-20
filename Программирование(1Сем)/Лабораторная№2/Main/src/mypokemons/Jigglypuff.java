

package mypokemons;

import mymoves.Jigglypuff.PlayNice;
import ru.ifmo.se.pokemon.Type;

public class Jigglypuff extends igglybuff {
    public Jigglypuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        setStats(115, 45, 20, 45, 25, 20);
        addMove(new PlayNice(0, 0));
    }
}
