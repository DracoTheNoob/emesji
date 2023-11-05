package fr.dtn.emesji.game.spell.system;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.game.living.creature.Creature;

public class Spell{
    protected final Game game;
    protected final Creature caster;
    protected final long cooldown;
    protected long currentCooldown;
    protected final double manaCost;
    protected final String icon;
    protected final SpellExecutor executor;

    public Spell(Game game, Creature caster, double cooldown, double manaCost, String icon, SpellExecutor executor){
        this.game = game;
        this.caster = caster;
        this.cooldown = (long)(cooldown*game.getFps());
        this.currentCooldown = 0;
        this.manaCost = manaCost;
        this.icon = "spell/icon/" + icon;
        this.executor = executor;
    }

    public void execute(){
        if(caster.getMana() >= manaCost && currentCooldown == 0 && executor.execute(game)){
            caster.setMana(caster.getMana() - manaCost);
            currentCooldown = cooldown;
        }
    }

    public void tick(){ currentCooldown = Math.max(currentCooldown-1, 0); }
    public long getCooldown() { return cooldown; }
    public long getCurrentCooldown() { return currentCooldown; }
    public String getIcon(){ return icon; }
}