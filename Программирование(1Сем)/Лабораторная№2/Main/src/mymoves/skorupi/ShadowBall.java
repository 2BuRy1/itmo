
package mymoves.skorupi;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove {
    public ShadowBall(double pow, double acc){
        super(Type.GHOST, pow , acc);




    }
    @Override
    protected String describe(){
        return "использует ShadowBall" ;
    }

    @Override
    public void applyOppEffects(Pokemon p){
        Effect e = new Effect().chance(0.2).stat(Stat.DEFENSE, -1);
        super.applyOppEffects(p);

        }
    }

