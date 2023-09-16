package fr.dtn.emesji.core.event;

public interface EventExecutor<E extends Event>{
    void execute(E event);
}