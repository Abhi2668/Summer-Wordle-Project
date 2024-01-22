import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Words {
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> create() {
        try {
            Scanner s = new Scanner(new File("sgb-words.txt"));
            while (s.hasNextLine()){
                list.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException f) {
            System.out.println("ow!");
        }
        return list;
    }

    // use this ArrayList if you want to attempt one of the extra credit options (accounting for duplicate characters)
    /**
     public static ArrayList<String> list = new ArrayList<>(Arrays.asList(
     "array", "catch", "class","error", "event", "hello", "inner", "javac", "merge", "nlogn", "queue", "scene"
     ));
     */
}
