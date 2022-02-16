package game.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Replay {

    public static final String REPLAY_DIR = System.getProperty("user.dir")+  "\\Replays\\";
    private static final String REPLAY_NAME = REPLAY_DIR+ "replay_";
    private static final int NREPLAYS = 5;
    private static File f;
    private static ArrayList<File> replays;


    static {
         f = new File(REPLAY_DIR);
         f.mkdir();
         replays = new ArrayList<>();
    }

    public static void SaveReplay(Object obj){


        ObjectOutputStream oos = null;
        String replayFileName;
        int i,j;
        boolean fileAlreadyExist = false;

        replays.clear();
        replays.addAll(Arrays.asList(f.listFiles()));

        for(i = 1; i <= replays.size();++i) {

            fileAlreadyExist = false;

            for(j = 1; j <= replays.size();++j)
                if (replays.get(i - 1).getAbsolutePath().equals(REPLAY_NAME + j)) {
                    fileAlreadyExist = true;
                    break;
                }
            if(!fileAlreadyExist)
                break;

        }


        if(i <=  NREPLAYS)
            replayFileName = REPLAY_NAME + i;
        else{
            replayFileName = REPLAY_NAME + NREPLAYS;
            renameFiles();

        }



        try {
            oos = new ObjectOutputStream(new FileOutputStream(replayFileName));
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


    }


    static Object LoadReplay(File f) {

        ObjectInputStream in = null;
        Object obj = null;

        if(!f.isFile())
            return null;


        if (f == null)
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

    static Object LoadReplay(int nReplay) {

        ObjectInputStream in = null;
        Object obj = null;
        File[] files = f.listFiles();
        if(files == null)
            return null;
        replays.clear();
        replays.addAll(Arrays.asList(files));

        File f = searchForFile(REPLAY_NAME + nReplay);
        //System.out.println(REPLAY_NAME + nReplay);

        if (f == null)
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

    private static void renameFiles(){
        StringBuilder sb = new StringBuilder();

        for (File replay : replays) {

            int lenght = replay.getAbsolutePath().length();
            sb.append(replay.getAbsolutePath());

            char c = sb.charAt(lenght - 1);
            if (c == '1')
                replay.delete();
            else {
                sb.setCharAt(lenght - 1, --c);
                replay.renameTo(new File(sb.toString()));

            }
            sb.delete(0, sb.length());
        }
    }

    private static File searchForFile (String name){

        for (File replay : replays) {
            if (replay.getAbsolutePath().equals(name))
                return replay;
        }
        return null;
    }



}



