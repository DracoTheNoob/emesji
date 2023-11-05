package fr.dtn.emesji.game.global;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.event.EventExecutor;
import fr.dtn.emesji.core.event.KeyEvent;
import fr.dtn.emesji.core.fx.Frame;
import fr.dtn.emesji.core.input.Key;
import fr.dtn.jll.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GlobalKeyHandler implements EventExecutor<KeyEvent>{
    @Override public void execute(KeyEvent e) {
        Game game = e.getGame();

        if(e.getKey() == Key.F2){
            Log.info("Taking screenshot");
            BufferedImage screen = game.getWindow().getScreen();
            String fileName = screen.getWidth() + "x" + screen.getHeight() + "-" + game.getCurrentFrame();
            File file = game.getFileManager().getFile("screenshots/" + fileName + ".png");

            try{
                System.out.println(file.getPath());

                if(!file.getParentFile().exists() && !file.getParentFile().mkdir())
                    throw new IOException("Failed to create screenshots directory");
                if(!file.createNewFile())
                    throw new IOException("Failed to create screenshot file");

                ImageIO.write(screen, "png", file);
                Log.info("Saved screenshot to " + file);
            }catch(IOException exception){
                Log.warn("Failed to take screenshot : " + exception.getMessage());
            }
        }else if(e.getKey() == Key.F11){
            Frame frame = game.getWindow().getFrame();
            int state = frame.getExtendedState();
            frame.setExtendedState(state == JFrame.NORMAL ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
        }
    }
}