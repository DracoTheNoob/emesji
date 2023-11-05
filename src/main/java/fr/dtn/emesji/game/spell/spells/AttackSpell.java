package fr.dtn.emesji.game.spell.spells;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.StatisticType;
import fr.dtn.emesji.game.spell.system.Spell;

import java.awt.geom.Ellipse2D;

public class AttackSpell extends Spell {
    public AttackSpell(Game game, Creature caster){
        super(game, caster, 20.0, 3.0, "attack", g -> {
            SpellCast spellCast = new SpellCast(game, caster);
            game.getScene().add(spellCast);
            return true;
        });
    }

    private static class SpellCast extends Sprite{
        private final Creature caster;

        public SpellCast(Game game, Creature caster){
            super(game, 0, caster.getLocation().copy(), 0, new Vector(1, 1), "spell/cast/attack");
            this.caster = caster;
        }

        @Override public void tick() {
            super.tick();

            double newScale = scale.getX() - .1 / game.getFps();
            this.scale = new Vector(newScale, newScale);

            if(this.scale.getX() < .005)
                game.getScene().remove(this.getId());

            if(game.getCurrentFrame() % 120 != 0)
                return;

            for(Sprite sprite : game.getScene().getSprites()){
                if(sprite == caster || !(sprite instanceof Creature creature))
                    continue;

                double width = texture.getWidth() * scale.getX();
                double height = texture.getHeight() * scale.getY();
                double x = location.getX() - width/2;
                double y = location.getY() - height/2;

                Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);

                if(ellipse.intersects(sprite.getCollision()))
                    creature.damage(caster, StatisticType.FIRE, 1);
            }
        }

        @Override public void init(){}
        @Override public void onAdd(Scene scene){}
        @Override public void onRemove(Scene scene){}
        @Override public void onCollide(Scene scene, Sprite collided){}
    }
}