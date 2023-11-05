package fr.dtn.emesji.game.hud.player;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.Panel;
import fr.dtn.emesji.core.fx.hud.LocatedHudElement;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.player.Player;
import fr.dtn.emesji.game.spell.system.Spell;

import java.awt.*;

public class HudSpellBar extends LocatedHudElement{
    private final int box = 50;
    private final Player player;
    private final Panel panel;

    public HudSpellBar(Game game, Player player) {
        super(game, new Vector(0, 0));

        this.player = player;
        this.panel = game.getWindow().getPanel();
    }

    @Override public void init(){}
    @Override public void tick(){
        this.location = new Vector(10, panel.getHeight() - box - 10);
    }

    @Override public void draw(Graphics2D g){
        Spell[] spells = player.getSpellBar().getSpells();

        for(int i = 0; i < spells.length; i++){
            Spell spell = spells[i];

            int drawX = (int)location.getX() + (box+10)*i;
            int drawY = (int)location.getY();

            g.drawImage(game.getTexture(spell.getIcon()), drawX, drawY, box, box, null);

            int offset = (int)(box * spell.getCurrentCooldown() / spell.getCooldown());
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(drawX, drawY+box-offset, box, offset);
        }
    }
}