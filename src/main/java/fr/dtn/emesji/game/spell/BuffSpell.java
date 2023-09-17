package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;
import fr.dtn.emesji.game.spell.Spell;

public class BuffSpell extends Spell{
    public BuffSpell(Game game, Creature caster){
        super(game, caster, 360, 1, "buff", g -> {
            caster.damage(caster.getHealth()*.5);
            return true;
        });
    }
}