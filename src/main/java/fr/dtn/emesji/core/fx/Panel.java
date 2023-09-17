package fr.dtn.emesji.core.fx;

import fr.dtn.emesji.core.Camera;
import fr.dtn.emesji.core.Game;
import fr.dtn.emesji.core.engine.Sprite;
import fr.dtn.emesji.core.math.Vector;
import fr.dtn.jll.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Panel extends JPanel{
    private final Game game;
    private BufferedImage render;

    public Panel(Game game){
        Log.info("Instantiating panel");

        this.game = game;

        Log.info("Instantiated panel");
    }

    @Override
    protected void paintComponent(Graphics graphics){
        this.render = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = this.render.createGraphics();

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        List<Sprite> sprites = Arrays.asList(game.getScene().getSprites());
        sprites.sort(Comparator.comparingInt(Sprite::getLayer));

        int offsetX = 0;
        int offsetY = 0;

        Camera camera = game.getScene().getCamera();
        if(camera != null && camera.getLocation() != null){
            offsetX = (int)camera.getLocation().getX();
            offsetY = (int)camera.getLocation().getY();
        }

        Rectangle window = new Rectangle(offsetX - getWidth()/2, offsetY-getHeight()/2, getWidth(), getHeight());

        for(Sprite sprite : sprites){
            Vector location = sprite.getLocation();
            BufferedImage texture = game.getTexture(sprite.getTextureName());
            Vector scale = sprite.getScale();

            if(!getCollision(location, texture, scale).intersects(window))
                continue;

            int width = texture.getWidth();
            int height = texture.getHeight();
            double x = location.getX();
            double y = location.getY();

            int realWidth = (int)(width * sprite.getScale().getX());
            int realHeight = (int)(height * sprite.getScale().getY());
            int drawX = (int)(getWidth() / 2 + x - realWidth / 2 - offsetX);
            int drawY = (int)(getHeight() / 2 - y - realHeight / 2 + offsetY);

            double angle = Math.toRadians(sprite.getAngle());

            g.translate(drawX, drawY);
            g.rotate(angle, realWidth / 2.0, realHeight / 2.0);

            g.drawImage(texture, 0, 0, realWidth, realHeight, null);
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
        graphics.drawImage(render, 0, 0, render.getWidth(), render.getHeight(), null);
    }

    private Rectangle getCollision(Vector location, BufferedImage texture, Vector scale){
        int textureW = (int)(texture.getWidth() * scale.getX());
        int textureH = (int)(texture.getHeight() * scale.getY());
        int textureX = (int)location.getX();
        int textureY = (int)location.getY();

        return new Rectangle(textureX - textureW/2, textureY - textureH/2, textureW, textureH);
    }

    public BufferedImage getScreen(){
        return render.getSubimage(0, 0, render.getWidth(), render.getHeight());
    }
}