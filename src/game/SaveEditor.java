package game;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * Created by Frank Williams on 6/4/2017.
 */
public class SaveEditor {
    public static File createFile() {
        File file = new File("CastleDefenseSave.txt");

        try{
            if (!file.exists()){
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                file.createNewFile();
                bw.write("n,0");
                if(Main.VERBOSE) System.out.println("File Created");
                bw.close();
            }else {
                if(Main.VERBOSE) System.out.println("File Already Exists");
            }
        }catch (IOException e){
            System.out.print("oops " + e.getMessage());
        } finally {

        }
        return file;
    }


    public static String[] readFile(File file) {

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line= br.readLine();
            System.out.println(line);
            return line.split(",");
        } catch (FileNotFoundException x) {
            System.out.println("FileNotFoundException when loading file: " + x.toString());
            return null;
        } catch (IOException ioe){
            System.out.println("IOException when loading file: " + ioe.toString());
            return null;
        }

    }

    public static void writeToFile(File file, String toWrite){
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(toWrite);
            bw.close();
        } catch (IOException x) {
            System.out.println("IOException when writing to file: " + x.toString());
        }
    }

    public static void writeToFile(File file, String[] toWrite){
        String s = toWrite[0] + ","+ toWrite[1];
        writeToFile(file,s);
    }

}
