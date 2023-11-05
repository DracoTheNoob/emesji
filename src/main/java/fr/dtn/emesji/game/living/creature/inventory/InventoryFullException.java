package fr.dtn.emesji.game.living.creature.inventory;

public class InventoryFullException extends RuntimeException{
    public InventoryFullException(){
        super("Unable to add item to inventory because there is not any available slot.");
    }
}