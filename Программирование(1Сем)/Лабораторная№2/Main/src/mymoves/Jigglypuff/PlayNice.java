

package mymoves.Jigglypuff;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;


import static ru.ifmo.se.pokemon.Type.NORMAL;

public class PlayNice extends StatusMove {
    public PlayNice(double pow, double acc) {
        super(NORMAL, pow, acc);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        p.setMod(Stat.ATTACK, -1);
    }
    @Override
    protected String describe(){
        return "Использует PlayNice";
    }


}
