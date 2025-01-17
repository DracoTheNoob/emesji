package fr.dtn.emesji.core.input;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.event.KeyEvent;
import fr.dtn.jll.Log;

public class KeyListener implements java.awt.event.KeyListener{
    private final Game game;
    private static final boolean[] keys = new boolean[526];

    public KeyListener(Game game){
        this.game = game;
    }

    @Override public void keyPressed(java.awt.event.KeyEvent e){
        if(!keys[e.getKeyCode()]){
            KeyEvent event = new KeyEvent(game,
                    Key.fromCode(e.getKeyCode())
            );

            game.on("key", event);
        }

        try{
            keys[e.getKeyCode()] = true;
        }catch(IndexOutOfBoundsException e1){
            Log.error("Key not handled in array (len " + keys.length + ") : " + e + "(" + e.getKeyLocation() + ")");
        }
    }

    @Override public void keyReleased(java.awt.event.KeyEvent e){
        try{
            keys[e.getKeyCode()] = false;
        }catch(IndexOutOfBoundsException e1){
            Log.error("Key not handled in array (len " + keys.length + ") : " + e + "(" + e.getKeyLocation() + ")");
        }
    }

    @Override public void keyTyped(java.awt.event.KeyEvent e){}

    public boolean[] getKeys(){ return keys; }
}