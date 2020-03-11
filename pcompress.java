import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        File f = new File();
        ArrayList<String> fdata = f.readf(args);
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
                System.out.println("Usage: punchcard [--verbose || -v] filename.pc");
                System.exit(0);
            }
        } else if (fileND[fileND.length-1].equals("pcc")) {   // PunchCode Compressed  (1-line)
            System.out.println("Program is already compressed.");
            System.exit(1);
        } else {
            System.out.println("Illegal file extension.\nUsage: pcompress [-n] or [--newfile ] filename.pc");
            System.exit(0);
        }
        return lines;
	}
}