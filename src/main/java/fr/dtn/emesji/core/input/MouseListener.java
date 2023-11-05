package fr.dtn.emesji.core.input;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.event.ClickEvent;
import fr.dtn.emesji.core.event.DragEvent;
import fr.dtn.emesji.core.math.Vector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {
    private final Game game;
    private final boolean[] clicks;
    private Vector last = new Vector(0, 0);

    public MouseListener(Game game){
        this.game = game;
        this.clicks = new boolean[MouseInfo.getNumberOfButtons()+1];
    }

    @Override public void mousePressed(MouseEvent e){ clicks[e.getButton()] = true; }
    @Override public void mouseReleased(MouseEvent e){ clicks[e.getButton()] = false; }
    @Override public void mouseClicked(MouseEvent e){
        ClickEvent event = new ClickEvent(game,
                e.getButton(),
                new Vector(e.getX(), e.getY())
        );

        game.on("click", event);
    }

    @Override public void mouseDragged(MouseEvent e){}
    @Override public void mouseMoved(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
}