import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LineCounter{
    private static final List<File> files = new ArrayList<>();
    
    private static void addFile(File file){
        if(!file.exists())
            return;

        if(file.isDirectory()){
            for(File subFile : Objects.requireNonNull(file.listFiles()))
                addFile(subFile);
        }else{
            files.add(file);
        }
    }

    private static int countLines(File file){
        try(FileReader read = new FileReader(file); BufferedReader reader = new BufferedReader(read)){
            return reader.lines().toList().size();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        addFile(new File("C:/Dev/Game/emesji/src/main/java/fr/dtn/emesji"));

        int sum = 0;

        for(File file : files)
            sum += countLines(file);

        System.out.println(sum);
    }
}