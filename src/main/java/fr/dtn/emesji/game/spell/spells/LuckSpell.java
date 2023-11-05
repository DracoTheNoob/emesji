package fr.dtn.emesji.game.spell.spells;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.Statistic;
import fr.dtn.emesji.game.spell.system.Spell;

public class LuckSpell extends Spell {
    public LuckSpell(Game game, Creature caster){
        super(game, caster, 20.0, 2.0, "luck", g -> {
            caster.getVarStats().add(Statistic.LUCK, 20, 10.0);
            return true;
        });
    }
}