package fr.dtn.emesji.game.hud;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.hud.FixedHudElement;
import fr.dtn.emesji.core.math.Vector;

import java.awt.*;

public abstract class HudBar extends FixedHudElement{
    private final Color border, background, foreground, splitter;

    private final int width;
    private final int height;
    private final int splitters;

    private double value;

    public HudBar(Game game, Vector location, int width, int height, int splitters, Color border, Color background, Color foreground, Color splitter) {
        super(game, location);

        this.width = width;
        this.height = height;
        this.splitters = splitters;

        this.border = border;
        this.background = background;
        this.foreground = foreground;
        this.splitter = splitter;
    }

    @Override public void init(){};

    @Override public abstract void tick();

    @Override public final void draw(Graphics2D g) {
        g.setColor(border);
        g.fillRect((int)(location.getX() - 3), (int)(location.getY() - 3), width + 6, height + 6);

        g.setColor(background);
        g.fillRect((int)location.getX(), (int)location.getY(), width, height);

        g.setColor(foreground);
        g.fillRect((int)location.getX(), (int)location.getY(), (int)(width * value), height);

        g.setColor(splitter);
        for(double i = 1.0; i < splitters+1; i++) {
            int x = (int)(location.getX() + i * width / splitters);
            g.drawLine(x, (int)(location.getY()), x, (int)(location.getY() + height - 1));
        }
    }

    protected final void setValue(double value) { this.value = value; }
}