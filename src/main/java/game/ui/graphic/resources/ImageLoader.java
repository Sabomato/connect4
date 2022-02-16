package game.ui.graphic.resources;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class ImageLoader {

    private static final String IMAGES_DIR = System.getProperty("user.dir") +
             String.format("%ssrc%smain%sresources%simages%s",
                    File.separator,File.separator,File.separator,
                    File.separator, File.separator);

    static HashMap<String, Image> imgCache;
    static {
        imgCache = new HashMap<>();
    }
    public static Image getImage(String name) {

        Image img = imgCache.get(name);

        if (img != null)
            return img;
        try {


            //System.err.println(IMAGES_DIR + name);
            img = new Image(Resources.getResourceFileAsStream(IMAGES_DIR + name));
            imgCache.put(name,img);
            return img;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Image getImageForce(String name) {
        imgCache.remove(name);
        return getImage(name);
    }
}

