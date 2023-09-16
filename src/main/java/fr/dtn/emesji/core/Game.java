package fr.dtn.emesji.core;

import fr.dtn.emesji.core.engine.Scene;
import fr.dtn.emesji.core.event.*;
import fr.dtn.emesji.core.fx.Window;
import fr.dtn.emesji.core.fx.hud.HudManager;
import fr.dtn.emesji.core.input.InputManager;
import fr.dtn.emesji.core.io.Json;
import fr.dtn.emesji.core.io.FileManager;
import fr.dtn.emesji.game.hud.HudMessage;
import fr.dtn.jll.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Game implements Cycle{
    private final int fpsLimit;
    private long currentFrame;

    private final HashMap<String, Json> staticData;
    private final HashMap<String, BufferedImage> textures;

    private final FileManager fileManager;
    private final Json configuration;
    private final Window window;
    private final InputManager input;
    private Camera camera;
    private Scene scene;
    private final HudManager hud;
    private final HudMessage hudMessage;
    private final EventManager event;

    public Game(FileManager fileManager){
        Log.info("Instantiating game");

        this.staticData = new HashMap<>();
        this.textures = new HashMap<>();

        this.fileManager = fileManager;
        this.configuration = new Json(fileManager.getFile("configuration.json"));
        this.window = new Window(this);
        this.input = new InputManager(this, window.getFrame());
        this.scene = new Scene(this);
        this.fpsLimit = configuration.getInt("window.fps", 120);
        this.hud = new HudManager();
        this.hudMessage = new HudMessage(this, window.getPanel());
        this.hud.addHudElement(hudMessage);
        this.event = new EventManager();

        Log.info("Instantiated game");
    }

    private void loadTextures(File file){
        if(!file.exists())
            return;

        if(file.isFile()){
            String fileName = file.getPath()
                    .replace(fileManager.getFile("/texture/").getPath()+"\\", "")
                    .replace("\\", "/");

            try{
                BufferedImage texture = ImageIO.read(file);
                String textureName = fileName.substring(0, fileName.lastIndexOf('.'));
                this.textures.put(textureName, texture);

                Log.info("'"+textureName+"' from '"+file.getPath()+"' loaded");
            }catch(IOException e){
                Log.warn("Unable to load " + fileName + " texture file : ignoring it");
            }

            return;
        }

        for(File subFile : Objects.requireNonNull(file.listFiles()))
            loadTextures(subFile);
    }

    public void run(){
        Log.info("Running game");

        this.init();
        currentFrame = 0;

        final double nsPerUpdate = 1000000000.0 / fpsLimit;

        long lastTime = System.nanoTime();
        double unprocessedTime = 0;
        int updates = 0;
        long frameCounter = System.currentTimeMillis();

        Log.info("Ran game");

        while(this.window.isVisible()){
            long currentTime = System.nanoTime();
            long passedTime = currentTime - lastTime;
            lastTime = currentTime;
            unprocessedTime += passedTime;

            if(unprocessedTime >= nsPerUpdate){
                unprocessedTime = 0;
                this.tick();
                currentFrame++;
                this.window.render();
                updates++;
            }

            if(System.currentTimeMillis() - frameCounter >= 1000){
                this.window.addToTitle(updates + " fps");

                updates = 0;
                frameCounter += 1000;
            }
        }

        Log.info("Closing game");

        this.window.close();
        this.scene.exit();

        Log.info("Closed game");
    }

    @Override public void init() {
        Log.info("Initializing game");
        Log.info("Loading textures");

        File textureFolder = fileManager.getFile("texture/");
        loadTextures(textureFolder);

        Log.info("Loaded textures");

        this.window.show();
        this.scene.init();
        this.hud.init();
        this.input.init();

        Log.info("Initialized game");
    }

    @Override public void tick() {
        this.scene.tick();
        this.hud.tick();
        this.input.tick();
    }

    public long getCurrentFrame(){ return currentFrame; }
    public int getFps(){ return fpsLimit; }
    public FileManager getFileManager(){ return fileManager; }
    public Json getConfiguration(){ return configuration; }
    public Window getWindow(){ return window; }
    public InputManager getInput() { return input; }
    public Scene getScene(){ return scene; }
    public void setScene(Scene scene){ this.scene = scene; }
    public HudManager getHudManager(){ return hud; }
    public void displayMessage(String message, long duration, int fontSize){ hudMessage.display(message, duration, fontSize); }

    public void setStaticData(String key, Json data){ this.staticData.put(key, data); }
    public Json getStaticData(String key){ return this.staticData.get(key); }
    public BufferedImage getTexture(String textureName){ return textures.get(textureName); }

    public void onKey(EventExecutor<KeyEvent> eventExecutor){ event.onKey(eventExecutor); }
    public void onClick(EventExecutor<ClickEvent> eventExecutor){ event.onClick(eventExecutor); }
    public void on(String eventName, Event event){ this.event.on(eventName, event); }
}