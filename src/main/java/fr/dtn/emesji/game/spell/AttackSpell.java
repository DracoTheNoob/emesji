package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.game.living.Creature;

public class AttackSpell extends Spell{
    public AttackSpell(Game game, Creature caster){
        super(game, caster, 240, 3, "attack", g -> {
            for(Sprite sprite : game.getScene().getSprites()){
                if(sprite.getLocation().distanceFrom(caster.getLocation()) > 1000)
                    continue;

                if(sprite instanceof Creature creature){
                    creature.damage(2);
                }
            }

            return true;
        });
    }
}