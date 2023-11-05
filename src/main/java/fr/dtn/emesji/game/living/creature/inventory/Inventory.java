package fr.dtn.emesji.game.living.creature.inventory;

public class Inventory{
    private final Slot[] slots;

    public Inventory(int size){
        this.slots = new Slot[size];
    }

    public Inventory(Slot[] slots){
        this.slots = slots;
    }

    public void setSlot(int slotId, Slot slot){ this.slots[slotId] = slot; }
    public void setAmount(int slotId, Slot slot){ this.slots[slotId] = slot; }
    public int getFirstOccurrenceOf(Item item){
        for(int i = 0; i < slots.length; i++)
            if(slots[i].getItem().equals(item))
                return i;

        return -1;
    }
    public void add(Item item, int amount){
        int firstEmpty = -1;

        for(int i = 0; i < slots.length; i++){
            if(slots[0] == null){
                firstEmpty = i;
                continue;
            }

            if(slots[i].getItem().equals(item)){
                slots[i].setAmount(slots[i].getAmount() + amount);
                return;
            }
        }

        if(firstEmpty == -1)
            throw new InventoryFullException();

        slots[firstEmpty] = new Slot(item, amount);
    }
    public int getFirstEmptySlot(){
        for(int i = 0; i < slots.length; i++)
            if(slots[i] == null)
                return i;

        return -1;
    }

    public boolean isFull(){
        return getFirstEmptySlot() == -1;
    }
}