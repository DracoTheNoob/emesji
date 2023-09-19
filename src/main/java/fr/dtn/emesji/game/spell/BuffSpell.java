package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.living.Statistic;

public class BuffSpell extends Spell{
    public BuffSpell(Game game, Creature caster){
        super(game, caster, 1800, 3, "buff", g -> {
            caster.getVarStats().add(Statistic.OFFENSIVE_MASTERY, 100, 1200, Statistic.BUFF_MASTERY);
            return true;
        });
    }
}