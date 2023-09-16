package fr.dtn.emesji.core.fx;

import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.jll.Log;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    private final Panel panel;

    public Frame(Game game){
        Log.info("Instantiating frame");

        this.panel = new Panel(game);

        Json config = game.getConfiguration().getJson("window");

        this.setTitle(config.getString("title", "Emesji"));
        this.setSize(config.getInt("size.width", 1080), config.getInt("size.height", 720));
        this.setMinimumSize(new Dimension(config.getInt("size.minWidth", 800), config.getInt("size.minHeight", 600)));
        this.setMaximumSize(new Dimension(config.getInt("size.maxWidth", 1920), config.getInt("size.maxHeight", 1080)));
        this.setResizable(config.getBoolean("resizable", true));
        this.setAlwaysOnTop(config.getBoolean("alwaysOnTop", false));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(panel);
        this.setIconImage(game.getFileManager().getImage(config.getString("icon", null)));
        this.setVisible(false);

        Log.info("Instantiated frame");
    }

    public Panel getPanel(){ return panel; }
}