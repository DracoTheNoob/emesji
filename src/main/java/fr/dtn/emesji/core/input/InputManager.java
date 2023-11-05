package fr.dtn.emesji.core.input;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.fx.Frame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class InputManager implements Cycle{
    private final Game game;
    private final Frame frame;

    private final List<JFrame> frames;
    private final List<KeyListener> keyListeners;
    private final List<MouseListener> mouseListeners;

    public InputManager(Game game, Frame frame){
        this.game = game;
        this.frame = frame;

        this.frames = new ArrayList<>();
        this.keyListeners = new ArrayList<>();
        this.mouseListeners = new ArrayList<>();
    }

    @Override public void init(){
        register(frame);

        frame.addWindowListener(new WindowListener() {
            @Override public void windowClosing(WindowEvent e){
                frames.forEach(JFrame::dispose);
            }

            @Override public void windowOpened(WindowEvent e){}
            @Override public void windowClosed(WindowEvent e){}
            @Override public void windowIconified(WindowEvent e){}
            @Override public void windowDeiconified(WindowEvent e){}
            @Override public void windowActivated(WindowEvent e){}
            @Override public void windowDeactivated(WindowEvent e){}
        });
    }

    @Override public void tick(){}

    public List<Key> getKeys(){
        List<Key> keys = new ArrayList<>();

        for(Key key : Key.values())
            if(isKey(key))
                keys.add(key);

        return keys;
    }

    public boolean isKey(Key key){
        if(keyListeners.isEmpty())
            return false;

        return keyListeners.get(0).getKeys()[key.getCode()];
    }

    public void register(JFrame frame){
        KeyListener keyListener = new KeyListener(game);
        MouseListener mouseListener = new MouseListener(game);

        frame.addKeyListener(keyListener);
        frame.addMouseListener(mouseListener);
        frame.addMouseMotionListener(mouseListener);

        this.frames.add(frame);
        this.keyListeners.add(keyListener);
        this.mouseListeners.add(mouseListener);
    }
}