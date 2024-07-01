
package mymoves.guzzlord;
import ru.ifmo.se.pokemon.*;



public class Confide extends StatusMove {

    public Confide(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
        super.applyOppEffects(p);

    }


    @Override
    protected String describe(){
        return "использует Confide";

        }
}

