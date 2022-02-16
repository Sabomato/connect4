package game.ui.graphic.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class Resources  {
    private Resources() {}
    public static InputStream getResourceFileAsStream(String name){

        //InputStream resource = Resources.class.getResourceAsStream(name);

        FileInputStream resource = null;
        try {
            resource = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return resource;

    }

    public static String getResourceFilename(String name){
        return Resources.class.getResource(name).toExternalForm();
    }
}
