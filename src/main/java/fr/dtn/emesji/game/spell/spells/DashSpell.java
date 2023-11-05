package fr.dtn.emesji.game.spell.spells;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.Statistic;
import fr.dtn.emesji.game.spell.system.Spell;

public class DashSpell extends Spell {
    public DashSpell(Game game, Creature caster){
        super(game, caster, 15.0, 2.5, "dash", g -> {
            caster.getVarStats().add(Statistic.SPEED, 200, 10.0, Statistic.BUFF_MASTERY);
            return true;
        });
    }
}