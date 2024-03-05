package mymoves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;




public class SampleMove extends PhysicalMove {
    public SampleMove(double pow, double acc) {
        super(Type.NORMAL, pow, acc);

    }

    @Override
    public void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
    }
    @Override
    public void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
    }
    @Override
    protected String describe(){
        return "использует LeafBlade" ;
    }
}



