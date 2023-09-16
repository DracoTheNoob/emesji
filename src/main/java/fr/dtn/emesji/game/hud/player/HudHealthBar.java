package fr.dtn.emesji.game.hud.player;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.hud.HudBar;
import fr.dtn.emesji.game.living.Player;

import java.awt.*;

public class HudHealthBar extends HudBar {
    private final Player player;

    public HudHealthBar(Game game, Player player) {
        super(
                game, new Vector(10, 10), 250, 20, 4,
                Color.black, Color.red, Color.green, Color.darkGray
        );

        this.player = player;
    }

    @Override public void tick(){
        this.setValue(player.getHealth() / player.getMaxHealth());
    }
}