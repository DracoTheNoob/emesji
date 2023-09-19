package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.living.Statistic;

public class DashSpell extends Spell{
    public DashSpell(Game game, Creature caster){
        super(game, caster, 1800, 2.5, "dash", g -> {
            caster.getVarStats().add(Statistic.SPEED, 200, 1200, Statistic.BUFF_MASTERY);
            return true;
        });
    }
}