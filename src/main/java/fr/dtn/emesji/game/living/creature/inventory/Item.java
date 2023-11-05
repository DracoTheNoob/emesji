package fr.dtn.emesji.game.living.creature.inventory;

import fr.dtn.emesji.core.io.Json;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final String name, description, icon;
    private final Rarity rarity;
    private final List<Slot> recipe;

    public Item(Json data){
        this.name = data.getString("name");
        this.description = data.getString("description");
        this.icon = data.getString("icon");
        this.rarity = Rarity.valueOf(data.getString("rarity"));
        this.recipe = new ArrayList<>();
        List<JSONObject> recipeList = data.getList("recipe", JSONObject.class);
        recipeList.forEach(object -> {
            Json slot = new Json(object);
            Item item = new Item(slot.getJson("item"));
            int amount = slot.getInt("amount");
            this.recipe.add(new Slot(item, amount));
        });
    }

    public String getName(){ return name; }
    public String getDescription(){ return description; }
    public String getIcon(){ return icon; }
    public Rarity getRarity(){ return rarity; }
    public List<Slot> getRecipe(){ return recipe; }
}