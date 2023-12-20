package mymoves.guzzlord;

import ru.ifmo.se.pokemon.*;
public class AirCutter extends SpecialMove {
    public AirCutter(double pow, double acc) {
        super(Type.FLYING, pow, acc);


    }

    @Override
    protected void applyOppDamage(Pokemon pokemon, double v) {
        super.applyOppDamage(pokemon, v);
    }

    @Override
    protected String describe() {
        return "использует AirCutter";
    }

    @Override
    public double calcCriticalHit(Pokemon att, Pokemon def) {
        if (att.getStat(Stat.SPEED) / (512.0 / 3) > Math.random()) {
            System.out.println("Критический удар!!");
            return 2.0;
        } else {
            return 1.0;
        }

    }
}




