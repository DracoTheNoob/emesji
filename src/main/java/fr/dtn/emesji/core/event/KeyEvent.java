package fr.dtn.emesji.core.event;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.input.Key;

public class KeyEvent extends Event{
    private final Key key;

    public KeyEvent(Game game, Key key){
        super(game);

        this.key = key;
    }

    public Key getKey(){ return key; }
}