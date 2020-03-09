import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        File f = new File();
        System.out.println(f.readf(args));
    }
}

public class File
{
	public ArrayList<String> readf(String[] args) {

        ArrayList<String> lines = new ArrayList<>();
        String[] fileND = args[args.length-1].split("\\.");

        if (args.length > 0) {
            if (fileND[fileND.length-1].equals("pc")) {
                try (Scanner s = new Scanner(new FileReader(String.join(".", fileND)))) {
                    while (s.hasNext()) {
                        lines.add(s.nextLine());
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println(String.join(".", fileND) + " not found.");
                }
            } else {
                System.out.println("Incorrect file extension.");
                System.exit(0);
            }
        } else {
            System.out.println("Insufficient arguments.");
            System.exit(0);
        }
        return lines;
	}
}

public class Lexer {
    public void lex() {
        //
    }
}

public class Parser {
    public void parse() {
        //
    }
}