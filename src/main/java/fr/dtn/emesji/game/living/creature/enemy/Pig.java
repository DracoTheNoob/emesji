package fr.dtn.emesji.game.living.creature.enemy;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Solid;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.hud.preset.CreatureHealthBar;
import fr.dtn.emesji.game.living.creature.Direction;
import fr.dtn.emesji.game.living.creature.player.Player;
import fr.dtn.emesji.game.living.statistics.StatisticType;

import java.awt.*;

public class Pig extends Creature implements Solid{
    private static final int LAYER = 5;
    private static final int ANGLE = 0;
    private static final Vector SCALE = new Vector(.2, .2);
    private static final String TEXTURE_NAME = "creature/pig";

    private long cooldown = 0;
    private final CreatureHealthBar healthBar;

    public Pig(Game game, Vector vector){
        super(game, LAYER, vector, ANGLE, SCALE, TEXTURE_NAME, "player", game.getStatistics("pig"));

        healthBar = new CreatureHealthBar(game, this);
        game.getHudManager().addHudElement(healthBar);
    }

    @Override public void onRemove(Scene scene){
        game.getHudManager().removeHudElement(healthBar);
    }

    @Override
    public void calculateCollision(){
        int width = (int)(texture.getWidth() * .8 * scale.getX());
        int height = (int)(texture.getHeight() * .5 * scale.getY());
        int x = (int)(location.getX() + texture.getWidth() * .1 * scale.getX());
        int y = (int)(location.getY());

        this.collision = new Rectangle(x - width/2, y - height/2, width, height);
    }

    @Override public void onCollide(Scene scene, Sprite collided){
        if(collided instanceof Player player && cooldown == 0){
            cooldown = 60;
            player.damage(this, StatisticType.EARTH, 2);
        }
    }

    @Override public void tick(){
        super.tick();

        if(game.getCurrentFrame() % 5 == 0)
            move(Direction.LEFT);

        cooldown = Math.max(cooldown-1, 0);
    }

    public Json toJson(){
        Json json = new Json();
        json.set("creature", super.toJson().toJsonObject());

        return json;
    }
}