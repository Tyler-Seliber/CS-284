package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class Exceptions {
    // checked exceptions: those that are declared in a THROWS clause of a declaration
    // checked is a misnomer: YOU must do the checking (or pass the buck with another THROWS)
    private static void readIt() throws IOException {
        File file = new File("Lec02/file.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int n = 1;
        String line = reader.readLine();
        while (line != null) {
            System.out.println(n + ": " + line);
            line = reader.readLine();
            n += 1;
        }

        reader.close();
        throw new RuntimeException("yolo");


    }

    public static void showBox(Box b) {
        if (b == null) {
            System.err.println("oh noes");
            return;
        }
    }

    public static void main(String[] args) {

        String s = null;
//            System.out.println(s.length());

        Box b = new Box();
//        System.out.println(b.s);
//        System.out.println(b.s.length());
        showBox(b);
        showBox(null);

        try {
            readIt();
        } catch (FileNotFoundException e) {
            System.out.println("well, where did you last see the file?");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ouch... bad file?");
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("oh, get it together.");
        }
    }
}

class Box {
    String s;
}
