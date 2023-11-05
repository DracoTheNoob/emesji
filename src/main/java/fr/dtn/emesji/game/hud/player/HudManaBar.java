package fr.dtn.emesji.game.hud.player;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.hud.preset.HudBar;
import fr.dtn.emesji.game.living.creature.player.Player;
import fr.dtn.emesji.game.living.statistics.Statistic;

import java.awt.*;

public class HudManaBar extends HudBar {
    private final Player player;

    public HudManaBar(Game game, Player player){
        super(
                game, new Vector(10, 40), 250, 15, 5,
                Color.black, Color.red, Color.cyan, Color.darkGray
        );

        this.player = player;
    }

    @Override public void tick(){
        this.setValue(player.getMana() / player.getStatistics().get(Statistic.MANA));
    }
}