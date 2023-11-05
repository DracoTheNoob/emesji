package fr.dtn.emesji.game.spell.spells;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.Statistic;
import fr.dtn.emesji.game.spell.system.Spell;

public class BuffSpell extends Spell {
    public BuffSpell(Game game, Creature caster){
        super(game, caster, 15.0, 3.0, "buff", g -> {
            caster.getVarStats().add(Statistic.OFFENSIVE_MASTERY, 100, 10.0, Statistic.BUFF_MASTERY);
            return true;
        });
    }
}