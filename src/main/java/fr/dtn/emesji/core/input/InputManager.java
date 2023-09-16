package fr.dtn.emesji.core.input;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.Frame;
import fr.dtn.jll.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputManager implements Cycle{
    private final Game game;
    private final Frame frame;

    private KeyListener keyListener;
    private MouseListener mouseListener;

    public InputManager(Game game, Frame frame){
        this.game = game;
        this.frame = frame;
    }

    @Override public void init(){
        this.keyListener = new KeyListener(game);
        this.mouseListener = new MouseListener(game);

        this.frame.addKeyListener(keyListener);
        this.frame.addMouseListener(mouseListener);
    }

    @Override public void tick(){}

    public List<Key> getKeys(){
        List<Key> keys = new ArrayList<>();

        for(int i = 0; i < keyListener.getKeys().length; i++)
            if(keyListener.getKeys()[i])
                keys.add(Key.fromCode(i));

        return keys;
    }

    public boolean isKey(Key key){ return keyListener.getKeys()[key.getCode()]; }
}