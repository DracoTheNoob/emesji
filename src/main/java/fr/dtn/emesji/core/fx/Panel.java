package fr.dtn.emesji.core.fx;

import fr.dtn.emesji.core.Camera;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.jll.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Panel extends JPanel{
    private final Game game;

    public Panel(Game game){
        Log.info("Instantiating panel");

        this.game = game;

        Log.info("Instantiated panel");
    }

    @Override
    protected void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        List<Sprite> sprites = Arrays.asList(game.getScene().getSprites());
        sprites.sort(Comparator.comparingInt(Sprite::getLayer));

        for(Sprite sprite : sprites){
            int offsetX = 0;
            int offsetY = 0;

            Camera camera = game.getScene().getCamera();
            if(camera.getLocation() != null){
                offsetX = (int)camera.getLocation().getX();
                offsetY = (int)camera.getLocation().getY();
            }

            BufferedImage image = sprite.getTexture();
            int width = image.getWidth();
            int height = image.getHeight();
            double x = sprite.getLocation().getX();
            double y = sprite.getLocation().getY();

            int realWidth = (int)(width * sprite.getScale().getX());
            int realHeight = (int)(height * sprite.getScale().getY());
            int drawX = (int)(getWidth() / 2 + x - realWidth / 2 - offsetX);
            int drawY = (int)(getHeight() / 2 - y - realHeight / 2 + offsetY);

            double angle = Math.toRadians(sprite.getAngle());

            g.translate(drawX, drawY);
            g.rotate(angle, realWidth / 2.0, realHeight / 2.0);

            g.drawImage(image, 0, 0, realWidth, realHeight, null);
            g.translate(-drawX, -drawY);

            Rectangle collision = sprite.getCollision();

            int collisionWidth = (int)collision.getWidth();
            int collisionHeight = (int)collision.getHeight();
            double collisionX = sprite.getLocation().getX();
            double collisionY = sprite.getLocation().getY();

            int collisionDrawX = (int)(getWidth() / 2 - collisionWidth / 2 + collisionX - offsetX);
            int collisionDrawY = (int)(getHeight() / 2 - collisionHeight / 2 - collisionY + offsetY);

            g.setColor(Color.magenta);
            g.drawRect(collisionDrawX, collisionDrawY, collisionWidth, collisionHeight);

            g.setTransform(AffineTransform.getRotateInstance(0));
            g.setTransform(AffineTransform.getTranslateInstance(0, 0));
        }

        game.getHudManager().draw(g);
    }
}