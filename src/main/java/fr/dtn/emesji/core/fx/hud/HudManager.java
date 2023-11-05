package fr.dtn.emesji.core.fx.hud;

import fr.dtn.emesji.core.Cycle;
import fr.dtn.emesji.core.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HudManager implements Cycle{
    private final Game game;
    private final List<HudElement> elements;

    public HudManager(Game game){
        this.game = game;
        this.elements = new ArrayList<>();
    }

    @Override public void init(){}
    @Override public void tick(){ elements.forEach(Cycle::tick); }

    public void addHudElement(HudElement element){
        this.elements.add(element);
        element.init();
    }

    public void removeHudElement(HudElement element){
        this.elements.remove(element);
    }

    public HudElement[] getElements(){ return elements.toArray(HudElement[]::new); }

    public void draw(Graphics2D g){ elements.forEach(element -> element.draw(g)); }
}