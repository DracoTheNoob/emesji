package fr.dtn.emesji.core.engine;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.jll.Log;

import java.util.HashMap;
import java.util.Map;

public abstract class AnimatedSprite extends Sprite{
    protected Map<String, Animation> animations;
    protected String currentAnimation;

    public AnimatedSprite(Game game, int layer, Vector vector, int angle, Vector scale, String texture){
        super(game, layer, vector, angle, scale, texture);

        this.animations = new HashMap<>();
    }

    public void registerAnimation(String name, Animation animation){
        if(animations.getOrDefault(name, null) != null){
            Log.warn("There is already an animation called '" + name + "' for sprite " + id);
            return;
        }

        this.animations.put(name, animation);
    }

    public void playAnimation(String name){
        Animation animation = animations.getOrDefault(name, null);

        if(animation == null){
            Log.warn("No animation called '" + name + "' for sprite " + id);
            return;
        }

        animation.play(this);
        this.currentAnimation = name;
    }

    public void stopAnimation(){
        for(String key : this.animations.keySet())
            this.animations.get(key).stop();
    }

    @Override public abstract void init();
    @Override public abstract void onAdd(Scene scene);
    @Override public abstract void onRemove(Scene scene);
    @Override public abstract void onCollide(Scene scene, Sprite collided);
}