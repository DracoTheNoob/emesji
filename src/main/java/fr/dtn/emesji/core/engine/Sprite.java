package fr.dtn.emesji.core.engine;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

public abstract class Sprite implements Cycle{
    protected final Game game;
    protected UUID id;
    protected int layer;
    protected Vector location;
    protected int angle;
    protected Vector scale;
    private String textureName;
    protected BufferedImage texture;
    protected Rectangle collision;
    protected Vector velocity;

    public Sprite(Game game, int layer, Vector location, int angle, Vector scale, String textureName){
        this.game = game;
        this.id = UUID.randomUUID();

        this.layer = layer;
        this.location = location;
        this.angle = angle;
        this.scale = scale;
        this.textureName = textureName;
        this.texture = game.getTexture(textureName);
        this.calculateCollision();
        this.velocity = new Vector(0, 0);
    }

    @Override public abstract void init();
    @Override public void tick(){
        this.calculateCollision();
    }

    public abstract void onAdd(Scene scene);
    public abstract void onRemove(Scene scene);
    public abstract void onCollide(Scene scene, Sprite collided);

    public void calculateCollision(){
        int width = (int)(texture.getWidth() * scale.getX());
        int height = (int)(texture.getHeight() * scale.getY());
        int x = (int) location.getX();
        int y = (int) location.getY();

        this.collision = new Rectangle(x - width/2, y - height/2, width, height);
    }

    public UUID getId(){ return id; }
    public int getLayer(){ return layer; }
    public Vector getLocation(){ return location; }
    public void setLocation(Vector vector){ this.location = vector; }
    public int getAngle(){ return angle; }
    public Vector getScale(){ return scale; }
    public String getTextureName(){ return textureName; }
    public BufferedImage getTexture(){ return texture; }
    public void setTexture(String textureName){
        this.textureName = textureName;
        this.texture = game.getTexture(textureName);
    }
    public Rectangle getCollision(){
        this.calculateCollision();
        return collision;
    }
    public void setVelocity(Vector velocity){ this.velocity = velocity; }

    public Vector getVelocity(){ return velocity; }
}