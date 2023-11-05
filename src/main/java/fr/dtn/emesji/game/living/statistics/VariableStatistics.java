package fr.dtn.emesji.game.living.statistics;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VariableStatistics implements Cycle{
    private final Game game;
    private final Creature creature;
    private final List<VariableStatistic> statistics;

    public VariableStatistics(Game game, Creature creature){
        this.game = game;
        this.creature = creature;
        this.statistics = new ArrayList<>();
    }

    @Override public void init(){}
    @Override public void tick(){
        statistics.forEach(VariableStatistic::tick);
        statistics.removeIf(stat -> stat.getDelay() <= 0);
    }

    public void add(Statistic statistic, double value, double delay, Statistic... canInteract){
        for(Statistic stat : canInteract){
            value += creature.getStatistics().get(stat);
            value += get(stat);
        }

        this.statistics.add(new VariableStatistic(statistic, value, (long)(delay * game.getFps())));
    }

    public double get(Statistic statistic){
        double toReturn = 0;

        for(VariableStatistic stat : getAll())
            if(stat.getStatistic() == statistic)
                toReturn += stat.getMultiplier();

        return toReturn;
    }
    public void remove(UUID id){
        this.statistics.removeIf(stat -> stat.getId() == id);
    }

    public VariableStatistic[] getAll(){ return statistics.toArray(VariableStatistic[]::new); }
}