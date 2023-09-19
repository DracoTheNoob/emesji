package fr.dtn.emesji.game.living;

import fr.dtn.emesji.core.io.Json;

import java.util.HashMap;

public class Statistics{
    private final HashMap<Statistic, Double> statistics;

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