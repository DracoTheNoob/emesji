package fr.dtn.emesji.core.fx.hud;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;

import java.awt.*;

public abstract class LocatedHudElement extends HudElement{
    protected Vector location;

    public LocatedHudElement(Game game, Vector location){
        super(game);

        this.location = location;
    }

    public abstract void draw(Graphics2D g);
}