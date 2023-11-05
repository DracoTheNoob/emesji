package fr.dtn.emesji.game.living.creature.player;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Solid;
import fr.dtn.emesji.core.input.Key;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.Creature;
import fr.dtn.emesji.game.hud.player.HudHealthBar;
import fr.dtn.emesji.game.hud.player.HudManaBar;
import fr.dtn.emesji.game.hud.player.HudSpellBar;
import fr.dtn.emesji.game.hud.player.HudVarStatsBar;
import fr.dtn.emesji.game.living.creature.Direction;
import fr.dtn.emesji.game.spell.system.SpellBar;
import fr.dtn.emesji.game.spell.spells.*;

public class Player extends Creature implements Solid{
    private static final int LAYER = 10;
    private static final int ANGLE = 0;
    private static final Vector SCALE = new Vector(5, 5);
    private static final String TEXTURE_NAME = "creature/player";

    private static HudHealthBar hudHealthBar;
    private static HudManaBar hudManaBar;
    private static HudSpellBar hudSpellBar;
    private static HudVarStatsBar hudVarStatsBar;

    private final SpellBar spellBar;

    public Player(Game game, Vector vector, Json statistics){
        super(game, LAYER, vector, ANGLE, SCALE, TEXTURE_NAME, "player", statistics);

        this.spellBar = new SpellBar(game);
        this.spellBar.add(new HealSpell(game, this));
        this.spellBar.add(new BuffSpell(game, this));
        this.spellBar.add(new AttackSpell(game, this));
        this.spellBar.add(new LuckSpell(game, this));
        this.spellBar.add(new DashSpell(game, this));
    }


    @Override public void init(){
        super.init();
    }

    @Override public void onAdd(Scene scene){
        handleHud();
    }

    @Override public void onRemove(Scene scene){
        game.displayMessage("DED", Long.MAX_VALUE, 50);
    }

    @Override public void tick(){
        super.tick();

        handleMovement();
        spellBar.tick();
        hudHealthBar.tick();
        hudManaBar.tick();
        hudSpellBar.tick();
    }

    private void handleMovement(){
        if(game.getInput().isKey(Key.Z) && game.getInput().isKey(Key.Q))
            move(Direction.TOP_LEFT);
        else if(game.getInput().isKey(Key.Z) && game.getInput().isKey(Key.D))
            move(Direction.TOP_RIGHT);
        else if(game.getInput().isKey(Key.S) && game.getInput().isKey(Key.Q))
            move(Direction.BOTTOM_LEFT);
        else if(game.getInput().isKey(Key.S) && game.getInput().isKey(Key.D))
            move(Direction.BOTTOM_RIGHT);
        else if(game.getInput().isKey(Key.Z))
            move(Direction.TOP);
        else if(game.getInput().isKey(Key.S))
            move(Direction.BOTTOM);
        else if(game.getInput().isKey(Key.Q))
            move(Direction.LEFT);
        else if(game.getInput().isKey(Key.D))
            move(Direction.RIGHT);
    }

    private void handleHud(){
        game.getHudManager().removeHudElement(hudHealthBar);
        game.getHudManager().removeHudElement(hudManaBar);
        game.getHudManager().removeHudElement(hudSpellBar);
        game.getHudManager().removeHudElement(hudVarStatsBar);

        hudHealthBar = new HudHealthBar(game, this);
        hudManaBar = new HudManaBar(game, this);
        hudSpellBar = new HudSpellBar(game, this);
        hudVarStatsBar = new HudVarStatsBar(game, this);

        game.getHudManager().addHudElement(hudHealthBar);
        game.getHudManager().addHudElement(hudManaBar);
        game.getHudManager().addHudElement(hudSpellBar);
        game.getHudManager().addHudElement(hudVarStatsBar);
    }

    public SpellBar getSpellBar(){ return spellBar; }

    public Json toJson(){
        Json json = new Json();

        // TODO

        return json;
    }
}