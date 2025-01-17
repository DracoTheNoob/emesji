package fr.dtn.emesji.game.global;

import fr.dtn.emesji.core.Camera;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.input.Key;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.emesji.game.living.creature.enemy.Pig;
import fr.dtn.emesji.game.living.creature.player.Player;

public class DebugScene extends Scene{
    public DebugScene(Game game){
        super(game);
    }

    @Override public void init(){
        for(int i = - 10; i < 20; i++)
            this.add(new Pig(game, new Vector(500, i*100)));

        Player player = new Player(game, new Vector(0, 0), game.getStatistics("player"));
        this.add(player);

        this.setCamera(new Camera(game, player.getId(), 1.0));

        super.init();
    }

    @Override public void tick(){
        super.tick();

        if(game.getInput().isKey(Key.SPACE)){
            for(Sprite sprite : getSprites())
                if(sprite instanceof Player)
                    return;

            Player player = new Player(game, new Vector((Math.random()-.5)*1000, (Math.random()-.5)*1000), game.getStatistics("player"));
            this.add(player);
            setCamera(new Camera(game, player.getId(), 1.0));

            game.displayMessage("", 0, 1);
        }
    }
}