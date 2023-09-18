package fr.dtn.emesji.core.engine;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.jll.Log;

import java.util.HashMap;
import java.util.Map;

public abstract class AnimatedSprite extends Sprite{
    protected Map<String, Animation> animations;
    protected String currentAnimation;

    public AnimatedSprite(Game game, int layer, Vector location, int angle, Vector scale, String texture){
        super(game, layer, location, angle, scale, texture);
        this.animations = new HashMap<>();
    }

    public AnimatedSprite(Game game, Json json){
        super(game, json.getJson("sprite"));

        Json animations = json.getJson("animations");
        this.animations = new HashMap<>();
        animations.toJsonObject().keySet().forEach(key ->
                this.animations.put(key.toString(), new Animation(animations.getJson(key.toString())))
        );
        this.currentAnimation = json.getString("currentAnimation");
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

    public Json toJson(){
        Json json = new Json();

        json.set("sprite", super.toJson());

        Json jsonAnimations = new Json();
        animations.keySet().forEach(key -> jsonAnimations.set(key, animations.get(key).toJson()));
        json.set("animations", jsonAnimations);
        json.set("currentAnimation", currentAnimation);

        return json;
    }
}