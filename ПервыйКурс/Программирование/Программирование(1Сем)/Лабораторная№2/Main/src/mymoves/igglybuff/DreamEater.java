
package mymoves.igglybuff;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {
    public DreamEater(double pow, double acc){
        super(Type.PSYCHIC, pow, acc);
    }



    @Override
    public void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (p.getCondition() == Status.SLEEP) {
            applyOppDamage(p, -100);



        } else {
            System.out.print("Покемончик не спит");
            applyOppDamage(p, +0 );
            System.out.println();

        }

    }
    @Override
        protected void applySelfEffects(Pokemon p){
            super.applySelfEffects(p);
            p.setMod(Stat.HP, + (int) ((p.getStat(Stat.HP) - p.getHP()) * 0.5));
            System.out.printf(p + " Восстановил %.2f Здоровья", p.getHP());


        }

    @Override
    protected String describe(){
        return "Использует DreamEater";
    }
}
