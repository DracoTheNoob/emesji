package fr.dtn.emesji.core.fx.hud;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;

import java.awt.*;
import java.util.UUID;

public abstract class HudElement implements Cycle{
    protected final Game game;
    private final UUID id;

    public HudElement(Game game){
        this.game = game;
        this.id = UUID.randomUUID();
    }

    public abstract void draw(Graphics2D g);

    public UUID getId(){ return id; }
}