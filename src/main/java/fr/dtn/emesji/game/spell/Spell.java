package fr.dtn.emesji.game.spell;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.Creature;

import java.awt.image.BufferedImage;

public class Spell{
    private final Game game;
    private final Creature caster;
    private final long cooldown;
    private long currentCooldown;
    private final double manaCost;
    private final String icon;
    private final SpellExecutor executor;

    public Spell(Game game, Creature caster, long cooldown, double manaCost, String icon, SpellExecutor executor){
        this.game = game;
        this.caster = caster;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
        this.manaCost = manaCost;
        this.icon = "spell/" + icon;
        this.executor = executor;
    }

    public int execute(){
        if(caster.getMana() >= manaCost) {
            if(currentCooldown == 0){
                executor.execute(game);
                currentCooldown = cooldown;
                caster.setMana(caster.getMana() - manaCost);
                return 1;
            }

            return -2;
        }

        return -1;
    }

    public void tick(){ currentCooldown = Math.max(currentCooldown-1, 0); }
    public long getCooldown() { return cooldown; }
    public long getCurrentCooldown() { return currentCooldown; }
    public String getIcon(){ return icon; }
}