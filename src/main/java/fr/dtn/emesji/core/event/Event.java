package fr.dtn.emesji.core.event;

import fr.dtn.emesji.core.Game;

public class Event {
    private final Game game;
    private final long frame;

    public Event(Game game){
        this.game = game;
        this.frame = game.getCurrentFrame();
    }

    public Game getGame(){ return game; }
    public long getFrame(){ return frame; }
}