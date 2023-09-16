package fr.dtn.emesji.core.engine;

import fr.dtn.emesji.core.Camera;
import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.jll.Log;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Scene implements Cycle{
    protected final Game game;
    private final List<Sprite> sprites;
    private final Map<UUID, Vector> locations;
    private Camera camera;

    private boolean first = true;

    public Scene(Game game){
        Log.info("Instantiating scene");

        this.game = game;
        this.sprites = new ArrayList<>();
        this.locations = new HashMap<>();

        Log.info("Instantiated scene");
    }

    @Override public void init(){
        Log.info("Initializing scene");

        sprites.forEach(Sprite::init);

        this.camera.init();

        Log.info("Initialized scene");
    }

    @Override public void tick(){
        for(Sprite sprite : getSprites()) {
            sprite.tick();

            double xMultiplier = 1;
            double yMultiplier = 1;

            for(Sprite other : getSprites()) {
                if(sprite == other)
                    continue;

                Rectangle base = other.getCollision();
                Rectangle extendedCollision = new Rectangle(
                        (int)base.getX()-2,
                        (int)base.getY()-2,
                        (int)base.getWidth()+4,
                        (int)base.getHeight()+4
                );

                if(sprite.getCollision().intersects(extendedCollision)){
                    sprite.onCollide(this, other);
                    other.onCollide(this, sprite);
                }

                if(!(sprite instanceof Solid))
                    continue;

                if(other instanceof Solid){
                    sprite.getLocation().translate(sprite.getVelocity().getX(), 0);
                    if(sprite.getCollision().intersects(other.getCollision()))
                        xMultiplier = 0;
                    sprite.getLocation().translate(-sprite.getVelocity().getX(), 0);

                    sprite.getLocation().translate(0, sprite.getVelocity().getY());
                    if(sprite.getCollision().intersects(other.getCollision()))
                        yMultiplier = 0;
                    sprite.getLocation().translate(0, -sprite.getVelocity().getY());
                }
            }

            sprite.getLocation().translate(sprite.getVelocity().getX() * xMultiplier, sprite.getVelocity().getY() * yMultiplier);
            sprite.setVelocity(new Vector(0, 0));
        }

        sprites.forEach(sprite -> locations.put(sprite.getId(), sprite.getLocation()));
        this.camera.tick();
    }

    public void exit(){
        for(Sprite sprite : sprites){
            sprite.onRemove(this);

            if(sprite instanceof AnimatedSprite animated)
                animated.stopAnimation();
        }
    }

    public void add(Sprite sprite){
        sprite.init();
        this.sprites.add(sprite);

        sprite.onAdd(this);
        Log.info("Sprite added : " + sprite.getId());
    }

    public void remove(UUID id){
        Sprite toRemove = null;

        for(Sprite sprite : getSprites()) {
            if(sprite.getId() == id){
                toRemove = sprite;
                break;
            }
        }

        if(toRemove == null)
            return;

        sprites.remove(toRemove);

        toRemove.onRemove(this);
        Log.info("Sprite removed : " + id);
    }

    public Sprite[] getSprites(){ return sprites.toArray(Sprite[]::new); }
    public Sprite getSprite(UUID id){
        for(Sprite sprite : getSprites())
            if(sprite.getId() == id)
                return sprite;

        return null;
    }

    public Camera getCamera(){ return camera; }
    public void setCamera(Camera camera){ this.camera = camera; }
}