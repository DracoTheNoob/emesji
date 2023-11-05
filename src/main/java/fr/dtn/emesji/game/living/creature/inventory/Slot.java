package fr.dtn.emesji.game.living.creature.inventory;

public class Slot{
    private Item item;
    private int amount;

    public Slot(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }

    public Item getItem(){ return item; }
    public void setItem(Item item){ this.item = item; }
    public int getAmount(){ return amount; }
    public void setAmount(int amount){ this.amount = amount; }
}