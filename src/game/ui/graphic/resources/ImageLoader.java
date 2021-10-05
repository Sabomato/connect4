package game.ui.graphic.resources;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImageLoader {

    private static final String IMAGES_DIR = "images/";
    static HashMap<String, Image> imgCache;
    static {
        imgCache = new HashMap<>();
    }
    public static Image getImage(String name) {

        Image img = imgCache.get(name);

        if (img != null)
            return img;
        try {

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

