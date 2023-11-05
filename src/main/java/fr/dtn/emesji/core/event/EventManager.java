package fr.dtn.emesji.core.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<EventExecutor<KeyEvent>> keyEvents;
    private final List<EventExecutor<ClickEvent>> clickEvents;
    private final List<EventExecutor<DragEvent>> dragEvents;

    public EventManager(){
        this.keyEvents = new ArrayList<>();
        this.clickEvents = new ArrayList<>();
        this.dragEvents = new ArrayList<>();
    }

    public void onKey(EventExecutor<KeyEvent> eventExecutor){ this.keyEvents.add(eventExecutor); }
    public void onClick(EventExecutor<ClickEvent> eventExecutor){ this.clickEvents.add(eventExecutor); }
    public void onDrag(EventExecutor<DragEvent> eventExecutor){ this.dragEvents.add(eventExecutor); }

    public void on(String eventName, Event event){
        switch(eventName){
            case "key" -> {
                if(event instanceof KeyEvent keyEvent)
                    keyEvents.forEach(ke -> ke.execute(keyEvent));
            } case "click" -> {
                if(event instanceof ClickEvent clickEvent)
                    clickEvents.forEach(ce -> ce.execute(clickEvent));
            } case "drag" -> {
                if(event instanceof DragEvent dragEvent)
                    dragEvents.forEach(de -> de.execute(dragEvent));
            }
        }
    }
}