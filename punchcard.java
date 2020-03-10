import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        File f = new File();
        Parser p = new Parser();
        String arg;

        List<String> flags = new ArrayList<String>();
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            if (arg.equals("-v") || arg.equals("--verbose")) {
                flags.add("verbose");
            } else {
                System.out.println("Illegal option: " + arg);
                System.exit(0);
            }
        }
        if (flags.contains("verbose")) {
            System.out.println(" [VERBOSE] File data: " + f.readf(args));
        }
        System.out.println(p.parse(f.readf(args)));
    }
}

public class File
{
	public ArrayList<String> readf(String[] args) {

        ArrayList<String> lines = new ArrayList<>();
        String[] fileND = args[args.length-1].split("\\.");

        if (fileND[fileND.length-1].equals("pc")) {
            if (args.length > 0) {
                try (Scanner s = new Scanner(new FileReader(String.join(".", fileND)))) {
                    while (s.hasNext()) {
                        lines.add(s.nextLine());
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println(String.join(".", fileND) + " not found.");
                }
            } else {
                System.out.println("Usage: punchcard [--verbose -v] filename");
                System.exit(0);
            }
        } else {
            System.out.println("Illegal file extension.\nUsage: punchcard [--verbose -v] filename");
            System.exit(0);
        }
        return lines;
	}
}

public class Parser {
    public String parse(ArrayList<String> lines) {
        boolean getc = true;
        String run = "";
        List<String> memory = new ArrayList<String>();
        int pointer = 0;

        for (String line : lines) {
            line = line.replaceAll("[^.\\s]","");
            if (getc) {
                if (line.equals(".  . . .")) {
                    getc = false;
                    run = "push";
                } else if (line.equals("  .. . . ")) {
                    getc = false;
                    run = "pop";
                } else if (line.equals("..      ")) {
                    getc = false;
                    run = "bak";
                } else if (line.equals("      ..")) {
                    getc = false;
                    run = "fwd";
                }
            } else {
                if (run == "push") {
                    String tmp = line.replace(".","1");
                    tmp = tmp.replace(" ", "0");
                    if (memory.size() == pointer) {
                        memory.add(Integer.toString(Integer.parseInt(tmp,2)));
                    } else {
                        memory.set(pointer,Integer.toString(Integer.parseInt(tmp,2)));
                    }

                    getc = true;
                    run = "";
                } else if (run == "pop") {
                    memory.set(pointer, "null");
                    run = "";
                } else if (run == "bak") {
                    pointer++;
                    run = "";
                } else if (run == "fwd") {
                    pointer--;
                    run = "";
                }
            }
        }
        return memory.toString();
    }
}