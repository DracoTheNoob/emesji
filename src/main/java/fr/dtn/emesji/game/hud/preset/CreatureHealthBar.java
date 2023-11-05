package fr.dtn.emesji.game.hud.preset;

import fr.dtn.emesji.core.Camera;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.Panel;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.living.statistics.Statistic;

import java.awt.*;


public class CreatureHealthBar extends HudBar{
    private final Creature target;

    public CreatureHealthBar(Game game, Creature target){
        super(
                game, new Vector(0, 0),
                (int)(target.getTexture().getWidth() * target.getScale().getX() * .75), 10,
                3, Color.black, Color.BLACK, Color.orange, Color.darkGray
        );

        this.target = target;
    }

    @Override public void tick(){
        Panel panel = game.getWindow().getPanel();

        int offsetX = 0;
        double offsetY = 0;

        Camera camera = game.getScene().getCamera();
        if(camera != null && camera.getLocation() != null){
            offsetX = (int)camera.getLocation().getX();
            offsetY = (int)camera.getLocation().getY();
        }

        Vector location = target.getLocation();
        Vector scale = target.getScale();

        int centerX = panel.getWidth() / 2;
        int centerY = panel.getHeight() / 2;
        double x = location.getX() - target.getTexture().getWidth() * scale.getX() * .375;
        double y = location.getY();
        offsetY -= target.getTexture().getHeight() * scale.getY() * .5;

        int drawX = (int)(centerX + x - offsetX);
        int drawY = (int)(centerY - y + offsetY);

        this.location = new Vector(drawX, drawY);
        this.setValue(target.getHealth() / target.getStatistics().get(Statistic.HEALTH));
    }
}