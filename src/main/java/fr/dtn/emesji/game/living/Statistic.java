package fr.dtn.emesji.game.living;

import static fr.dtn.emesji.game.living.StatisticType.NEUTRAL;
import static fr.dtn.emesji.game.living.StatisticType.FIRE;
import static fr.dtn.emesji.game.living.StatisticType.EARTH;
import static fr.dtn.emesji.game.living.StatisticType.WATER;
import static fr.dtn.emesji.game.living.StatisticType.THUNDER;
import static fr.dtn.emesji.game.living.StatisticType.OFFENSIVE;
import static fr.dtn.emesji.game.living.StatisticType.DEFENSIVE;
import static fr.dtn.emesji.game.living.StatisticType.HEAL;
import static fr.dtn.emesji.game.living.StatisticType.BUFF;

public enum Statistic{
    HEALTH(NEUTRAL), MANA(NEUTRAL), SPEED(NEUTRAL), LUCK(NEUTRAL),
    FIRE_DEFENSE(FIRE), EARTH_DEFENSE(EARTH), WATER_DEFENSE(WATER), THUNDER_DEFENSE(THUNDER),
    FIRE_MASTERY(FIRE), EARTH_MASTERY(EARTH), WATER_MASTERY(WATER), THUNDER_MASTERY(THUNDER),
    OFFENSIVE_MASTERY(OFFENSIVE), DEFENSIVE_MASTERY(DEFENSIVE), HEAL_MASTERY(HEAL), BUFF_MASTERY(BUFF);

    private final StatisticType type;

    Statistic(StatisticType type){
        this.type = type;
    }

    public StatisticType getType(){ return type; }

    public static Statistic getMastery(StatisticType type){
        return switch(type){
            case OFFENSIVE -> OFFENSIVE_MASTERY;
            case DEFENSIVE -> DEFENSIVE_MASTERY;
            case HEAL -> HEAL_MASTERY;
            case BUFF -> BUFF_MASTERY;

            case FIRE -> FIRE_MASTERY;
            case EARTH -> EARTH_MASTERY;
            case WATER -> WATER_MASTERY;
            case THUNDER -> THUNDER_MASTERY;
            default -> null;
        };
    }

    public static Statistic getDefense(StatisticType type){
        return switch(type){
            case FIRE -> FIRE_DEFENSE;
            case EARTH -> EARTH_DEFENSE;
            case WATER -> WATER_DEFENSE;
            case THUNDER -> THUNDER_DEFENSE;
            default -> null;
        };
    }
}