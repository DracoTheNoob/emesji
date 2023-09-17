package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;

public class HealSpell extends Spell{
    public HealSpell(Game game, Creature caster){
        super(game, caster, 240, 3, "heal", g -> {
            if(caster.getHealth() < caster.getMaxHealth()) {
                caster.heal(4);
                return true;
            }

            return false;
        });
    }
}