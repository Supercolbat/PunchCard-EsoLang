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
        ArrayList<String> fdata = f.readf(args);
        if (flags.contains("verbose")) {
            Parser.verbose("File data:"+fdata);
        }
        List<String> pdata = p.parse(fdata,flags);
        for (String out : pdata) {
			System.out.println(out);
		}
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
                System.out.println("Usage: punchcard [--verbose || -v] filename");
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
    public static void verbose(String s) {if (flagl.contains("verbose")) {System.out.println(" [VERBOSE] "+s);}}
    private static void warn(String s) {System.out.println(" [WARNING] "+s);}
    public static List<String> flagl = new ArrayList<String>();
    public List<String> parse(ArrayList<String> lines, List<String> flags) {
        boolean getc = true;
        String run = "";
        List<String> memory = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        int pointer = 0;
        flagl = flags;

        for (String line : lines) {
			if (line.charAt(0) != ';') {
                if (line.length() >= 8) {
    	            if (line.length() > 8) {
	                    line = line.replaceAll("[^.\\s]","").substring(0,8);
	                }
	                if (getc) {
    	                if (line.equals(".  . . .")) {
        	                getc = false;
            	            run = "push";
                	    } else if (line.equals(" .. . . ")) {
                    	    memory.set(pointer, "");
                            Parser.verbose("cmd: pop");
                        
        	            } else if (line.equals("..      ")) {
            	            pointer--;
                            Parser.verbose("cmd: bak");
                            Parser.verbose("pointer: "+pointer);
    	                } else if (line.equals("      ..")) {
        	                pointer++;
                            Parser.verbose("cmd: fwd");
                            Parser.verbose("pointer: "+pointer);
	                    } else if (line.equals(" .      ")) {
    	                    memory.set(0,Character.toString((char) Integer.parseInt(memory.get(pointer))));
        	            } else if (line.equals("  .     ")) {
            	            memory.set(0,Integer.toString(Integer.parseInt(memory.get(pointer))+48));
                	    } else if (line.equals(".  ..  .")) {
							String merged = String.join("",memory);
							memory.clear();
	                        memory.add(merged);
    	                } else if (line.equals(".  ..  ..")) {
							List<String> bef = new ArrayList<String>();
        	                memory.add("");
							bef.addAll(memory.subList(0,pointer-1));
							bef.add(String.join("",memory.subList(pointer,memory.size()-1)));
							memory.clear();
							memory.addAll(bef);
							memory.remove(memory.size()-1);
                	    } else if (line.equals("..  ..  .")) {
							memory.add("");
                            List<String> aft = new ArrayList<String>();
                            memory.add("");
                            aft.addAll(memory.subList(pointer+1,memory.size()-1));
							aft.add(0,String.join("",memory.subList(0,pointer+1)));
							memory.clear();
							memory.addAll(aft);
							memory.remove(memory.size()-1);
	                    } else if (line.equals(".       ")) {
    	                    output.add(memory.get(pointer));
                            Parser.verbose("Pushed to memory: "+memory.get(pointer));
							memory.set(pointer,"");
                        } else {
                            Parser.warn(" [Warning] Unknown line. Skipping. It may be useful, so check if it's written correctly:\n"+line);
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
                            Parser.verbose("cmd: push");
                            Parser.verbose("pushed: "+tmp);
                            
                            getc = true;
                            run = "";
                        }
                    }
                } else {
                    Parser.verbose("Comment found");
                }
            }
        }
        return output;
    }
}
