package fr.dtn.emesji.game.living;

import fr.dtn.emesji.core.io.Json;
import fr.dtn.jll.Log;

import java.util.HashMap;

public class Statistics{
    private final HashMap<Statistic, Double> statistics;

    public Statistics(
            double health, double mana, double speed,
            double fireDefense, double earthDefense, double waterDefense, double thunderDefense,
            int fireMastery, int earthMastery, int waterMastery, int thunderMastery,
            int offensiveMastery, int defensiveMastery, int healMastery, int buffMastery
    ){
        this.statistics = new HashMap<>();

        set(Statistic.HEALTH, health);
        set(Statistic.MANA, mana);
        set(Statistic.SPEED, speed);
        set(Statistic.FIRE_DEFENSE, fireDefense);
        set(Statistic.EARTH_DEFENSE, earthDefense);
        set(Statistic.WATER_DEFENSE, waterDefense);
        set(Statistic.THUNDER_DEFENSE, thunderDefense);
        set(Statistic.FIRE_MASTERY, fireMastery);
        set(Statistic.EARTH_MASTERY, earthMastery);
        set(Statistic.WATER_MASTERY, waterMastery);
        set(Statistic.THUNDER_MASTERY, thunderMastery);
        set(Statistic.OFFENSIVE_MASTERY, offensiveMastery);
        set(Statistic.DEFENSIVE_MASTERY, defensiveMastery);
        set(Statistic.HEAL_MASTERY, healMastery);
        set(Statistic.BUFF_MASTERY, buffMastery);
    }

    public Statistics(Json json){
        this.statistics = new HashMap<>();

        read(json);
    }
    public void read(Json json){
        for(Statistic statistic : Statistic.values())
            set(statistic, json.getDouble(statistic.name(), 0.0));
    }

    public void set(Statistic statistic, double value){ this.statistics.put(statistic, value); }
    public double get(Statistic statistic){ return this.statistics.get(statistic); }

    public Json toJson(){
        Json json = new Json();

        statistics.keySet().forEach(key -> json.set(key.name(), statistics.get(key)));

        return json;
    }
}