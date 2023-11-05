package fr.dtn.emesji.game.living.creature.inventory;

import java.awt.*;

public enum Rarity{
    COMMON(new Color(221, 221, 221)),
    UNORDINARY(new Color(98, 180, 46)),
    RARE(new Color(255, 190, 97)),
    LEGENDARY(new Color(255, 255, 97)),
    MYSTICAL(new Color(249, 0, 250)),
    UNIQUE(new Color(0, 186, 255));

    private final Color color;

    Rarity(Color color){ this.color = color; }

    public Color getColor(){ return color; }
}
