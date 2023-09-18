package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.spell.Spell;

public class BuffSpell extends Spell{
    public BuffSpell(Game game, Creature caster){
        super(game, caster, 1200, 3, "buff", g -> {
            // TODO
            return true;
        });
    }
}