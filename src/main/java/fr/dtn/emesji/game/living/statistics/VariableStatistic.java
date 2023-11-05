package fr.dtn.emesji.game.living.statistics;

import fr.dtn.emesji.core.Cycle;

import java.util.UUID;

public class VariableStatistic implements Cycle{
    private final UUID id;
    private final Statistic statistic;
    private double multiplier;
    private final long baseDelay;
    private long delay;

    public VariableStatistic(Statistic statistic, double multiplier, long baseDelay){
        this.id = UUID.randomUUID();
        this.statistic = statistic;
        this.multiplier = multiplier;
        this.baseDelay = baseDelay;
        this.delay = baseDelay;
    }

    @Override public void init(){}

    @Override public void tick(){
        delay--;
    }

    public UUID getId(){ return id; }
    public Statistic getStatistic(){ return statistic; }

    public double getMultiplier(){ return multiplier; }
    public void addMultiplier(int multiplier){ this.multiplier += multiplier; }

    public long getDelay(){ return delay; }
    public long getBaseDelay(){ return baseDelay; }

    @Override
    public String toString() {
        return "VariableStatistic{" +
                "id=" + id +
                ", statistic=" + statistic +
                ", multiplier=" + multiplier +
                ", baseDelay=" + baseDelay +
                ", delay=" + delay +
                '}';
    }
}