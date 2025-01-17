package fr.dtn.emesji.game.living.creature;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.*;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.statistics.Statistic;
import fr.dtn.emesji.game.living.statistics.StatisticType;
import fr.dtn.emesji.game.living.statistics.Statistics;
import fr.dtn.emesji.game.living.statistics.VariableStatistics;
import fr.dtn.emesji.game.spell.system.SpellBar;

public class Creature extends AnimatedSprite implements Solid{
    protected final Statistics statistics;
    protected final VariableStatistics varStats;
    protected final String name;
    protected final SpellBar spellBar;

    private double health;
    private double mana;

    public Creature(Game game, int layer, Vector vector, int angle, Vector scale, String texture, String name, Json statistics) {
        super(game, layer, vector, angle, scale, texture);

        this.name = name;
        this.spellBar = new SpellBar(game);
        this.statistics = new Statistics(statistics);
        this.varStats = new VariableStatistics(game, this);

        this.health = this.statistics.get(Statistic.HEALTH);
        this.mana = this.statistics.get(Statistic.MANA);
    }

    public void move(Direction direction, double speedMultiplier){
        double s = statistics.get(Statistic.SPEED) * (1+varStats.get(Statistic.SPEED)*0.01);

        switch(direction){
            case TOP_LEFT -> this.velocity = new Vector(-s * speedMultiplier / Math.sqrt(2), s * speedMultiplier / Math.sqrt(2));
            case TOP -> this.velocity = new Vector(0, s * speedMultiplier);
            case TOP_RIGHT -> this.velocity = new Vector(s * speedMultiplier / Math.sqrt(2), s * speedMultiplier / Math.sqrt(2));
            case LEFT -> this.velocity = new Vector(-s * speedMultiplier, 0);
            case RIGHT -> this.velocity = new Vector(s * speedMultiplier, 0);
            case BOTTOM_LEFT -> this.velocity = new Vector(-s * speedMultiplier / Math.sqrt(2), -s * speedMultiplier / Math.sqrt(2));
            case BOTTOM -> this.velocity = new Vector(0, -s * speedMultiplier);
            case BOTTOM_RIGHT -> this.velocity = new Vector(s * speedMultiplier / Math.sqrt(2), -s * speedMultiplier / Math.sqrt(2));
        }
    }

    public void move(Direction direction){ move(direction, 1); }

    @Override public void init(){}

    @Override public void tick(){
        spellBar.tick();

        double maxMana = this.statistics.get(Statistic.MANA);
        this.mana = Math.min(this.mana + maxMana*(.05/120), maxMana);
        this.varStats.tick();
    }

    @Override public void onAdd(Scene scene){}
    @Override public void onRemove(Scene scene){}
    @Override public void onCollide(Scene scene, Sprite collided){}

    public void damage(Creature damager, StatisticType type, double damage){
        double finalDamage = damager == null ? damage : critic(damager, damage);

        finalDamage *= 1 - 0.01*this.statistics.get(Statistic.getDefense(type));
        finalDamage *= 1 - 0.01*this.statistics.get(Statistic.DEFENSIVE_MASTERY);
        finalDamage *= 1 - 0.01*this.varStats.get(Statistic.getDefense(type));
        finalDamage *= 1 - 0.01*this.varStats.get(Statistic.DEFENSIVE_MASTERY);

        if(damager != null){
            finalDamage *= 1 + 0.01*damager.statistics.get(Statistic.OFFENSIVE_MASTERY);
            finalDamage *= 1 + 0.01*damager.statistics.get(Statistic.getMastery(type));
            finalDamage *= 1 + 0.01*damager.varStats.get(Statistic.OFFENSIVE_MASTERY);
            finalDamage *= 1 + 0.01*damager.varStats.get(Statistic.getMastery(type));
        }

        this.health = Math.max(this.health - finalDamage, 0);

        if(this.health == 0)
            kill();
    }

    public void heal(Creature healer, StatisticType type, double heal){
        double finalHeal = healer == null ? heal : critic(healer, heal);

        if(healer != null){
            finalHeal *= 1 + 0.01 * healer.statistics.get(Statistic.HEAL_MASTERY);
            finalHeal *= 1 + 0.01 * healer.statistics.get(Statistic.getMastery(type));
            finalHeal *= 1 + 0.01 * healer.varStats.get(Statistic.HEAL_MASTERY);
            finalHeal *= 1 + 0.01 * healer.varStats.get(Statistic.getMastery(type));
        }

        this.health = Math.min(this.health + finalHeal, this.statistics.get(Statistic.HEALTH));
    }

    public double critic(Creature user, double value){
        double luck = user.getStatistics().get(Statistic.LUCK)*0.01;
        luck += user.getVarStats().get(Statistic.LUCK)*0.01;
        boolean critic = Math.random() < luck;
        return value * (critic ? 1.5 : 1);
    }

    public void kill(){
        this.health = 0;
        game.getScene().remove(this.getId());
    }

    public Statistics getStatistics(){ return statistics; }
    public VariableStatistics getVarStats(){ return varStats; }
    public String getName(){ return name; }
    public SpellBar getSpellBar(){ return spellBar; }

    public double getHealth(){ return health; }
    public double getMana(){ return mana; }
    public void setMana(double mana){ this.mana = mana; }
    public void setHealth(double health){ this.health = health; }

    public Json toJson(){
        Json json = new Json();

        json.set("animatedSprite", super.toJson());
        json.set("statistics", statistics.toJson());
        json.set("name", name);
        json.set("spellBar", spellBar.toJson());

        return json;
    }
}