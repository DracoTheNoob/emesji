package fr.dtn.emesji.game.living;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.*;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.spell.Spell;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Creature extends AnimatedSprite implements Solid{
    private final String name;
    protected double maxHealth;
    private double health;
    private double lastHealth;
    protected double maxMana;
    private double mana;
    private double lastMana;
    protected double speed;

    private final List<Spell> spells;

    public Creature(Game game, int layer, Vector vector, int angle, Vector scale, String texture, String name) {
        super(game, layer, vector, angle, scale, texture);

        this.name = name;
        this.spells = new ArrayList<>();
    }

    public void move(Direction direction, double speedMultiplier){
        switch(direction){
            case TOP_LEFT -> this.velocity = new Vector(-speed * speedMultiplier / Math.sqrt(2), speed * speedMultiplier / Math.sqrt(2));
            case TOP -> this.velocity = new Vector(0, speed * speedMultiplier);
            case TOP_RIGHT -> this.velocity = new Vector(speed * speedMultiplier / Math.sqrt(2), speed * speedMultiplier / Math.sqrt(2));
            case LEFT -> this.velocity = new Vector(-speed * speedMultiplier, 0);
            case RIGHT -> this.velocity = new Vector(speed * speedMultiplier, 0);
            case BOTTOM_LEFT -> this.velocity = new Vector(-speed * speedMultiplier / Math.sqrt(2), -speed * speedMultiplier / Math.sqrt(2));
            case BOTTOM -> this.velocity = new Vector(0, -speed * speedMultiplier);
            case BOTTOM_RIGHT -> this.velocity = new Vector(speed * speedMultiplier / Math.sqrt(2), -speed * speedMultiplier / Math.sqrt(2));
        }
    }

    public void addSpell(Spell spell){
        spells.add(spell);
    }

    public int useSpell(Class<? extends Spell> spellClass){
        for(Spell spell : spells)
            if(spell.getClass() == spellClass)
                return spell.execute();

        return 0;
    }

    public void move(Direction direction){ move(direction, 1); }

    @Override public void init() {

    }

    @Override public void tick(){
        if(health <= 0)
            game.getScene().remove(getId());

        spells.forEach(Spell::tick);
    }

    @Override public void onAdd(Scene scene) {

    }

    @Override public void onRemove(Scene scene) {

    }

    @Override public void onCollide(Scene scene, Sprite collided) {

    }

    public void damage(double damage){ this.health = Math.max(health - damage, 0); }
    public void heal(double heal){ this.health = Math.min(health + heal, getMaxHealth()); }

    public double getMaxHealth() { return maxHealth; }
    public double getHealth() { return health; }
    public double getLastHealth() { return lastHealth; }

    public double getMaxMana() { return maxMana; }
    public double getMana() { return mana; }
    public double getLastMana() { return lastMana; }

    public void setLastHealth(double lastHealth) { this.lastHealth = lastHealth; }
    public void setHealth(double health){
        this.lastHealth = this.health;
        this.health = health;
    }

    public void setLastMana(double lastMana) { this.lastMana = lastMana; }
    public void setMana(double mana){
        this.lastMana = this.mana;
        this.mana = mana;
    }

    public Spell[] getSpells(){ return spells.toArray(Spell[]::new); }

    public Json toJson(){
        Json json = new Json();

        json.set("id", getId().toString());
        json.set("layer", getLayer());

        Json location = new Json();
        location.set("x", getLocation().getX());
        location.set("y", getLocation().getY());
        json.set("location", location.toJsonObject());

        json.set("angle", getAngle());

        Json scale = new Json();
        scale.set("x", getScale().getX());
        scale.set("y", getScale().getY());
        json.set("scale", scale.toJsonObject());

        json.set("texture", getTextureName());

        Json collision = new Json();
        collision.set("x", getCollision().getX());
        collision.set("y", getCollision().getY());
        collision.set("width", getCollision().getWidth());
        collision.set("height", getCollision().getHeight());
        json.set("collision", collision.toJsonObject());

        Json velocity = new Json();
        velocity.set("x", getVelocity().getX());
        velocity.set("y", getVelocity().getY());
        json.set("velocity", velocity.toJsonObject());

        Json animations = new Json();
        for(String animationName : super.animations.keySet()){
            Animation animation = super.animations.get(animationName);
            Json jsonAnimation = new Json();

            jsonAnimation.set("duration", animation.getDuration());
            jsonAnimation.set("steps", animation.getSteps());
            animations.set(animationName, jsonAnimation.toJsonObject());
        }
        json.set("animations", animations.toJsonObject());
        json.set("currentAnimation", super.currentAnimation);

        json.set("name", name);

        json.set("maxHealth", maxHealth);
        json.set("health", health);
        json.set("lastHealth", lastHealth);

        json.set("maxMana", maxMana);
        json.set("mana", mana);
        json.set("lastMana", lastMana);

        json.set("speed", speed);

        return json;
    }
}