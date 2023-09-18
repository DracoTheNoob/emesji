package fr.dtn.emesji.core.engine;

import fr.dtn.emesji.core.io.Json;
import fr.dtn.jll.Log;
import org.json.simple.JSONArray;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Animation{
    private final double duration;
    private final String[] steps;
    private final Timer timer;
    private int currentStep;

    public Animation(double duration, String... steps){
        if(steps == null || steps.length == 0)
            throw new RuntimeException("Animation steps can't be null");

        this.duration = duration;
        this.steps = steps;
        this.timer = new Timer();
        this.currentStep = 0;
    }

    public Animation(Json json){
        this.duration = json.getDouble("duration");
        this.steps = json.getList("steps", String.class).toArray(String[]::new);
        this.timer = new Timer();
        this.currentStep = json.getInt("currentStep");
    }

    public void play(Sprite sprite){
        long stepDuration = (int)(duration / steps.length * 1000);

        this.timer.schedule(new TimerTask(){
            @Override public void run(){
                sprite.setTexture(steps[currentStep]);
                currentStep++;

                if(currentStep == steps.length)
                    currentStep = 0;
            }
        }, 0, stepDuration);
    }

    public void stop(){
        this.timer.purge();
        this.timer.cancel();
    }

    public double getDuration(){ return duration; }
    public String[] getSteps(){ return steps; }

    public Json toJson(){
        Json json = new Json();

        json.set("duration", duration);
        JSONArray stepsArray = new JSONArray();
        stepsArray.addAll(Arrays.asList(steps));
        json.set("steps", stepsArray);
        json.set("currentStep", currentStep);

        return json;
    }
}