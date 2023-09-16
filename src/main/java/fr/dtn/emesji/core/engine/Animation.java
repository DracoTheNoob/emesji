package fr.dtn.emesji.core.engine;

import fr.dtn.jll.Log;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Animation{
    private final double duration;
    private final String[] steps;
    private final Timer timer;

    public Animation(double duration, String... steps){
        if(steps == null || steps.length == 0)
            throw new RuntimeException("Animation steps can't be null");

        this.duration = duration;
        this.steps = steps;
        this.timer = new Timer();
    }

    public void play(Sprite sprite){
        long stepDuration = (int)(duration / steps.length * 1000);

        this.timer.schedule(new TimerTask(){
            private int currentStep = 0;

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
}