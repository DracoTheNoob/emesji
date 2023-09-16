package fr.dtn.emesji.game;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Solid;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.math.Vector;

public class Block extends Sprite implements Solid{
    public Block(Game game, int x, int y, String texture){
        super(game, 0, new Vector(x, y), 0, new Vector(5, 5), "world/"+texture);
    }

    @Override public void init(){}
    @Override public void onAdd(Scene scene){}
    @Override public void onRemove(Scene scene){}
    @Override public void onCollide(Scene scene, Sprite collided){}
}