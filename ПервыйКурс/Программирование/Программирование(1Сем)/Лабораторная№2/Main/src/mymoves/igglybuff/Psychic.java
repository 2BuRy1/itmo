
package mymoves.igglybuff;

import ru.ifmo.se.pokemon.*;

public class Psychic extends SpecialMove {
    public Psychic(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);

    }



    @Override
    public void applyOppEffects(Pokemon p) {
        Effect e = new Effect().chance(0.1).stat(Stat.DEFENSE, -1);
        super.applyOppEffects(p);
    }
    @Override
    protected String describe() {
        return "Использует Psychic";
    }
}

