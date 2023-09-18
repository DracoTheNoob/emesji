package fr.dtn.emesji.game.living;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.input.Key;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.game.spell.Spell;
import fr.dtn.jll.Log;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SpellBar implements Cycle{
    private final Game game;
    private final List<Spell> spells;

    public SpellBar(Game game){
        this.game = game;
        this.spells = new ArrayList<>();
    }

    @Override public void init(){}

    @Override public void tick(){
        spells.forEach(Spell::tick);

        Key[] keys = { Key.ONE, Key.TWO, Key.THREE, Key.FOUR, Key.FIVE, Key.SIX };

        for(int i = 0; i < Math.min(keys.length, spells.size()); i++)
            if(game.getInput().isKey(keys[i]))
                useSpell(spells.get(i).getClass());
    }

    public void add(Spell spell){ this.spells.add(spell); }

    public void useSpell(Class<? extends Spell> spellClass){
        for(Spell spell : spells){
            if(spell.getClass() == spellClass){
                spell.execute();
                return;
            }
        }
    }

    public Spell[] getSpells(){ return spells.toArray(Spell[]::new); }

    public Json toJson(){
        Json json = new Json();

        JSONArray list = new JSONArray();
        spells.forEach(spell -> list.add(spell.getClass().getName()));
        json.set("spells", list);

        return json;
    }
}