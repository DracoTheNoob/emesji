package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.living.Statistic;

public class LuckSpell extends Spell{
    public LuckSpell(Game game, Creature caster){
        super(game, caster, 2400, 2, "luck", g -> {
            caster.getVarStats().add(Statistic.LUCK, 20, 1200);
            return true;
        });
    }
}