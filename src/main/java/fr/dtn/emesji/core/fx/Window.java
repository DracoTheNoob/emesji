package fr.dtn.emesji.core.fx;

import fr.dtn.emesji.core.Game;
import fr.dtn.jll.Log;

import java.awt.image.BufferedImage;

public class Window{
    private final Frame frame;
    private final Panel panel;
    private final String baseTitle;

    public Window(Game game){
        Log.info("Instantiating window");

        this.frame = new Frame(game);
        this.panel = frame.getPanel();
        this.baseTitle = this.frame.getTitle();

        Log.info("Instantiated window");
    }

    public void render(){
        if(frame.isVisible())
            this.panel.repaint();
    }

    public void show(){
        this.frame.setVisible(true);
        Log.info("Shown window");
    }

    public void hide(){
        this.frame.setVisible(false);
        Log.info("Hided window");
    }

    public void close(){
        this.frame.dispose();
        Log.info("Closed window");
    }

    public void addToTitle(String add){ this.frame.setTitle(baseTitle + " - " + add); }

    public boolean isVisible(){ return this.frame.isVisible(); }
    public Frame getFrame(){ return frame; }
    public Panel getPanel(){ return panel; }

    public BufferedImage getScreen(){ return panel.getScreen(); }
}