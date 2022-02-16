package game.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public  class Save {

    public static final String SAVES_DIR = System.getProperty("user.dir")+  "\\Saves\\";

    private  static ArrayList<File> saves;
    private  static File f;

    static {
        saves = new ArrayList<>();
        f = new File(SAVES_DIR);
        f.mkdir();

    }
    public static boolean SaveFile(File f, Object obj){
        ObjectOutputStream oos = null;

        File aux = searchForFile( f.getPath());
        if(aux != null)
            return false;


        try {

            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(oos!=null)
                    oos.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return true;


    }

    public static  boolean SaveFile(String name,Object obj){

        ObjectOutputStream oos = null;
        //System.out.println(f.listFiles());
        saves.clear();
        saves.addAll(Arrays.asList(f.listFiles()));

        File aux = searchForFile( SAVES_DIR +name);
        if(aux != null)
            return false;


        try {

            oos = new ObjectOutputStream(new FileOutputStream(SAVES_DIR +name));
            oos.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(oos!=null)
                    oos.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return true;
    }

    public  static Object LoadFile(String fileName){


        ObjectInputStream in = null;
        Object obj = null;

        saves.clear();
        saves.addAll(Arrays.asList(f.listFiles()));

        File aux = searchForFile(SAVES_DIR + fileName);

        if (aux == null)
            return null;

        try {
            in = new ObjectInputStream(new FileInputStream(aux));
            obj = in.readObject();

            if (in != null)
                in.close();
            return obj;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();


            }
        }
        return  obj;


    }

    public  static Object LoadFile(File f){


        ObjectInputStream in = null;
        Object obj = null;


        if(!f.isFile())
            return null;

        try {
            in = new ObjectInputStream(new FileInputStream(f));
            obj = in.readObject();

            if (in != null)
                in.close();
            return obj;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return  obj;

    }

    private  static File searchForFile (String name){

        for (File save : saves) {
            if (save.getAbsolutePath().equals(name))
                return save;
        }
        return null;
    }
}
