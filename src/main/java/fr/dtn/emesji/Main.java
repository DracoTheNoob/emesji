package fr.dtn.emesji;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.io.FileManager;
import fr.dtn.emesji.game.global.DebugScene;
import fr.dtn.emesji.game.global.GlobalKeyHandler;
import fr.dtn.jll.Log;

public class Main{
    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        Log.setDirectory(fileManager.getFile("log"));

        Game game = new Game(fileManager);
        game.onKey(new GlobalKeyHandler());
        game.setScene(new DebugScene(game));
        game.run();
    }
}