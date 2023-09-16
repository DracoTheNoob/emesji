package fr.dtn.emesji.core.io;

import fr.dtn.jll.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManager{
    private static final java.nio.file.Path root = Paths.get(System.getProperty("user.home"));
    private static final String DIRECTORY_NAME = "Emesji";

    static{
        File directory = Paths.get(root.toString(), DIRECTORY_NAME).toFile();

        if(!directory.exists() && !directory.mkdir())
            throw new RuntimeException("Unable to create game directory");
    }

    public File getFile(String file){
        if(file == null || file.length() == 0)
            return Paths.get(root.toString(), DIRECTORY_NAME).toFile();

        return Paths.get(root.toString(), DIRECTORY_NAME, file).toFile();
    }

    public BufferedImage getImage(String file){
        try{
            return ImageIO.read(getFile(file));
        }catch(IOException e){
            Log.warn("Unable to load image '" + file + "'");
            return null;
        }
    }
}