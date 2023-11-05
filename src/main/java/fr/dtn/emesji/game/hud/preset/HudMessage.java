package fr.dtn.emesji.game.hud.preset;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.Panel;
import fr.dtn.emesji.core.fx.hud.LocatedHudElement;
import fr.dtn.emesji.core.math.Vector;

import java.awt.*;

public class HudMessage extends LocatedHudElement {
    private final Panel panel;
    private String message;
    private long duration;
    private int fontSize;


    public HudMessage(Game game, Panel panel){
        super(game, new Vector(0, 0));
        this.panel = panel;

        this.message = "";
        this.duration = 0;
        this.fontSize = 12;
    }

    @Override public void init(){}
    @Override public void tick(){
        duration = Math.max(duration -1, 0);
    }
    @Override public void draw(Graphics2D g){
        if(duration == 0)
            return;

        double x = panel.getWidth() / 2.0 - fontSize*Math.sqrt(message.length()) * .9;
        double y = panel.getHeight() / 2.0 + fontSize / 2.0;
        location = new Vector(x, y);

        g.setFont(new Font("Javanese Text", Font.ITALIC, fontSize));
        g.setColor(Color.orange);
        g.drawString(message, (int)location.getX(), (int)location.getY());
    }

    public void display(String message, long duration, int fontSize){
        this.message = message;
        this.duration = duration;
        this.fontSize = fontSize;
    }
}