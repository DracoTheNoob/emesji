package fr.dtn.emesji.core.event;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;

public class ClickEvent extends Event{
    private final int click;
    private final Vector location;

    public ClickEvent(Game game, int click, Vector location){
        super(game);

        this.click = click;
        this.location = location;
    }

    public int getClick(){ return click; }
    public Vector getLocation(){ return location; }
}