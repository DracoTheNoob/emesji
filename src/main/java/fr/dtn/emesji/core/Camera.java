package fr.dtn.emesji.core;

import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.math.Vector;

import java.util.UUID;

public class Camera implements Cycle{
    private final Game game;
    private UUID target;
    private Vector location;
    private double zoom;

    public Camera(Game game, UUID target, double zoom){
        this.game = game;
        this.target = target;
        this.zoom = zoom;
    }

    @Override public void init() {

    }

    @Override public void tick(){
        Sprite sprite = game.getScene().getSprite(target);
        if(sprite == null)
            return;
        this.location = sprite.getLocation();
    }

    public UUID getTarget(){ return target; }
    public Vector getLocation(){ return location; }
}