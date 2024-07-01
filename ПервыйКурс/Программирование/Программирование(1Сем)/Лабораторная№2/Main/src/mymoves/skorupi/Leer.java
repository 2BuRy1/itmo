
package mymoves.skorupi;


import ru.ifmo.se.pokemon.*;

import static ru.ifmo.se.pokemon.Stat.DEFENSE;

public class Leer extends StatusMove {

    public Leer(double pow, double acc){
        super(Type.NORMAL, pow ,acc);
    }
    @Override
    public void applyOppEffects(Pokemon def){
        def.setMod(DEFENSE , -1);
        Effect e = new Effect().chance(1).stat(DEFENSE, -1);
        super.applyOppEffects(def);

    }


    @Override
    protected String describe(){
        return "использует Leer" ;
    }
    }

