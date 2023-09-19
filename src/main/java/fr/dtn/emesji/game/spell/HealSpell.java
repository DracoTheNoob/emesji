package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.living.Statistic;
import fr.dtn.emesji.game.living.StatisticType;

public class HealSpell extends Spell{
    public HealSpell(Game game, Creature caster){
        super(game, caster, 360, 2, "heal", g -> {
            if(caster.getHealth() < caster.getStatistics().get(Statistic.HEALTH)){
                caster.heal(caster, StatisticType.WATER, 1);
                return true;
            }

            return false;
        });
    }
}