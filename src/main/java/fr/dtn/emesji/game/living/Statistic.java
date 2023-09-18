package fr.dtn.emesji.game.living;

public enum Statistic{
    HEALTH(StatisticType.NEUTRAL), MANA(StatisticType.NEUTRAL), SPEED(StatisticType.NEUTRAL),
    FIRE_DEFENSE(StatisticType.FIRE), EARTH_DEFENSE(StatisticType.EARTH), WATER_DEFENSE(StatisticType.WATER), THUNDER_DEFENSE(StatisticType.THUNDER),
    FIRE_MASTERY(StatisticType.FIRE), EARTH_MASTERY(StatisticType.EARTH), WATER_MASTERY(StatisticType.WATER), THUNDER_MASTERY(StatisticType.THUNDER),
    OFFENSIVE_MASTERY(StatisticType.OFFENSIVE), DEFENSIVE_MASTERY(StatisticType.DEFENSIVE), HEAL_MASTERY(StatisticType.HEAL), BUFF_MASTERY(StatisticType.BUFF);

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