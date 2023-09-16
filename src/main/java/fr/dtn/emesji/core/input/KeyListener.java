package fr.dtn.emesji.core.input;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.event.KeyEvent;
import fr.dtn.jll.Log;

public class KeyListener implements java.awt.event.KeyListener{
    private final Game game;
    private final boolean[] keys;

    public KeyListener(Game game){
        this.game = game;
        this.keys = new boolean[526];
    }

    @Override public void keyTyped(java.awt.event.KeyEvent e){
        KeyEvent event = new KeyEvent(game,
                Key.fromCode(e.getKeyCode())
        );

        game.on("key", event);
    }

    @Override public void keyPressed(java.awt.event.KeyEvent e){
        try{
            this.keys[e.getKeyCode()] = true;
        }catch(IndexOutOfBoundsException e1){
            Log.error("Key not handled in array (len " + keys.length + ") : " + e + "(" + e.getKeyLocation() + ")");
        }
    }

    @Override public void keyReleased(java.awt.event.KeyEvent e){
        try{
            this.keys[e.getKeyCode()] = false;
        }catch(IndexOutOfBoundsException e1){
            Log.error("Key not handled in array (len " + keys.length + ") : " + e + "(" + e.getKeyLocation() + ")");
        }
    }

    public boolean[] getKeys(){ return keys; }
}