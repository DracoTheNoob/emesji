package fr.dtn.emesji.core.fx.hud;

import fr.dtn.emesji.core.Cycle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HudManager implements Cycle{
    private final List<HudElement> elements;

    public HudManager(){
        this.elements = new ArrayList<>();
    }

    @Override public void init(){}

    @Override public void tick(){
        elements.forEach(Cycle::tick);
    }

    public void addHudElement(HudElement element){
        this.elements.add(element);
        element.init();
    }

    public void removeHudElement(HudElement element){
        this.elements.remove(element);
    }

    public HudElement[] getElements(){ return elements.toArray(HudElement[]::new); }
    public HudElement getElement(UUID id){
        for(HudElement element : getElements())
            if(element.getId() == id)
                return element;

        return null;
    }

    public void draw(Graphics2D g){
        elements.forEach(element -> element.draw(g));
    }
}