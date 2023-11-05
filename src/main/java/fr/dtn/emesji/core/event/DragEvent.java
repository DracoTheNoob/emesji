package fr.dtn.emesji.core.event;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.math.Vector;

public class DragEvent extends Event{
    private final Vector start;
    private final Vector drag;

    public DragEvent(Game game, Vector start, Vector drag){
        super(game);
        this.start = start;
        this.drag = drag;
    }

    public Vector getStart(){ return start; }
    public Vector getDrag(){ return drag; }
}