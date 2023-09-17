package fr.dtn.emesji.game.living;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Solid;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.input.Key;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.hud.player.HudHealthBar;
import fr.dtn.emesji.game.hud.player.HudManaBar;
import fr.dtn.emesji.game.hud.player.HudSpellBar;
import fr.dtn.emesji.game.spell.AttackSpell;
import fr.dtn.emesji.game.spell.BuffSpell;
import fr.dtn.emesji.game.spell.HealSpell;

import java.util.Arrays;

public class Player extends Creature implements Solid{
    private static final int LAYER = 10;
    private static final int ANGLE = 0;
    private static final Vector SCALE = new Vector(5, 5);
    private static final String TEXTURE_NAME = "creature/player";

    private static HudHealthBar healthBar;
    private static HudManaBar manaBar;
    private static HudSpellBar spellBar;

    private int level;
    private long experience;

    public Player(Game game, Vector vector){
        super(game, LAYER, vector, ANGLE, SCALE, TEXTURE_NAME, "player");
    }


    @Override public void init(){
        super.init();

        Json staticData = game.getStaticData("player");

        if(staticData == null){
            this.maxHealth = 10.0;
            this.setHealth(maxHealth);
            this.setLastHealth(getHealth());

            this.maxMana = 12.0;
            this.setMana(maxMana);
            this.setLastMana(getMana());

            addSpell(new HealSpell(game, this));
            addSpell(new BuffSpell(game, this));
            addSpell(new AttackSpell(game, this));

            this.speed = 2;
        }

        // TODO : READ STATIC DATA
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
        handleSpells();
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

    private void handleSpells(){
        Key[] keys = { Key.ONE, Key.TWO, Key.THREE, Key.FOUR, Key.FIVE, Key.SIX };

        for(int i = 0; i < Math.min(keys.length, getSpells().length); i++)
            if(game.getInput().isKey(keys[i]))
                useSpell(getSpells()[i].getClass());
    }

    private void handleHud(){
        game.getHudManager().removeHudElement(healthBar);
        game.getHudManager().removeHudElement(manaBar);
        game.getHudManager().removeHudElement(spellBar);

        healthBar = new HudHealthBar(game, this);
        manaBar = new HudManaBar(game, this);
        spellBar = new HudSpellBar(game, this);

        game.getHudManager().addHudElement(healthBar);
        game.getHudManager().addHudElement(manaBar);
        game.getHudManager().addHudElement(spellBar);
    }

    public Json toJson(){
        Json json = new Json();
        json.set("creature", super.toJson().toJsonObject());

        json.set("level", level);
        json.set("experience", experience);

        return json;
    }
}