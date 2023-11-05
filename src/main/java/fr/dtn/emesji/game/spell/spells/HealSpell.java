package fr.dtn.emesji.game.spell.spells;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.Statistic;
import fr.dtn.emesji.game.living.statistics.StatisticType;
import fr.dtn.emesji.game.spell.system.Spell;

public class HealSpell extends Spell {
    public HealSpell(Game game, Creature caster){
        super(game, caster, 3.0, 2.0, "heal", g -> {
            if(caster.getHealth() < caster.getStatistics().get(Statistic.HEALTH)){
                caster.heal(caster, StatisticType.WATER, 1);
                return true;
            }

            return false;
        });
    }
}