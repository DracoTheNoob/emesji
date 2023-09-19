package fr.dtn.emesji.game.hud.player;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.hud.FixedHudElement;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.Player;
import fr.dtn.emesji.game.living.VariableStatistic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HudVarStatsBar extends FixedHudElement{
    private final int box = 40;
    private final Player player;

    public HudVarStatsBar(Game game, Player player){
        super(game, new Vector(0, 0));

        this.player = player;
    }

    @Override public void init(){}
    @Override public void tick(){
        this.location = new Vector(10, 60);
    }

    @Override public void draw(Graphics2D g){
        VariableStatistic[] stats = player.getVarStats().getAll();

        for(int i = 0; i < stats.length; i++){
            VariableStatistic stat = stats[i];

            int drawX = (int)location.getX();
            int drawY = (int)location.getY() + (box+10)*i;

            String textureName = "effect/" + stat.getStatistic().name().toLowerCase();
            BufferedImage texture = game.getTexture(textureName);
            g.drawImage(texture, drawX, drawY, box, box, null);

            int offset = (int)(box * stat.getDelay() / stat.getBaseDelay());
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(drawX, drawY+box-offset, box, offset);
        }
    }
}