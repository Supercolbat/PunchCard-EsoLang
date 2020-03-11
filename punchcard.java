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
            } else if (arg.equals("-h") || arg.equals("--help")) {
                System.out.println("punchcard [-hv] or [--help --verbose] file.[pc pcc]");
                System.exit(2);
            } else {
                System.out.println("Illegal option: " + arg);
                System.exit(0);
            }
        }
        ArrayList<String> fdata = f.readf(args);
        if (flags.contains("verbose")) {
            System.out.println(" [VERBOSE] File data:"+fdata);
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
                System.out.println("Usage: punchcard [-hv] or [--help --verbose] filename");
                System.exit(0);
            }
        } else if (fileND[fileND.length-1].equals("pcc")) {   // PunchCode Compressed  (1-line)
            try (Scanner s = new Scanner(new FileReader(String.join(".", fileND)))) {
                for (String chunk : s.nextLine().split("(?<=\\G.{8})")) {
                    lines.add(chunk);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(String.join(".", fileND) + " not found.");
            }
        } else {
            System.out.println("Illegal file extension. Usage: punchcard [-hv] or [--help --verbose] filename.[pc pcc]");
            System.exit(0);
        }
        return lines;
	}
}

public class Parser {
    private static int indline = 0;
    public static void verbose(String s) {if (flagl.contains("verbose")) {System.out.println(" [VERBOSE:"+indline+"] "+s);}}
    private static void warn(String s) {System.out.println(" [WARNING:"+indline+"] "+s);}
    private static void error(String s) {System.out.println(" [ERROR:"+indline+"] "+s); System.exit(0);}
    public static List<String> flagl = new ArrayList<String>();

    private static List<String> memory = new ArrayList<String>();
    private static List<String> output = new ArrayList<String>();
    private static ArrayList<String> formem = new ArrayList<String>();
    private static int pointer = 0;

    public List<String> parse(ArrayList<String> lines, List<String> flags) {
        boolean getc = true;
        String run = "";
        flagl = flags;
        // For variables
        boolean fFlag = false;
        int fRep = 0;

        for (String line : lines) {
            indline++;
			if (line.length() > 0 && line.charAt(0) != ';') {
                if (line.length() >= 8) {
    	            if (line.length() > 8) {
	                    line = line.replaceAll("[^.\\s]","").substring(0,8);
	                }
	                if (getc) {
    	                if (line.equals(".  . . .")) {
        	                getc = false;
            	            run = "push";
                            Parser.verbose("cmd: push");
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
    	                    memory.set(pointer,Character.toString((char) Integer.parseInt(memory.get(pointer))));
                            Parser.verbose("Converted to char: "+memory.get(pointer));
        	            } else if (line.equals("  .     ")) {
            	            memory.set(pointer,Integer.toString(Integer.parseInt(memory.get(pointer))+48));
                            Parser.verbose("Converted to int: "+memory.get(pointer));
                	    } else if (line.equals(".  ..  .")) {
							String merged = String.join("",memory);
							memory.clear();
	                        memory.add(merged);
                            Parser.verbose("Merged all to pointer");
                            Parser.verbose(memory.toString());
    	                } else if (line.equals(".  .. ..")) {
							List<String> bef = new ArrayList<String>();
        	                memory.add("tmp");
							bef.addAll(memory.subList(0,pointer));
							bef.add(String.join("",memory.subList(pointer,memory.size()-1)));
							memory.clear();
							memory.addAll(bef);
                            Parser.verbose("Merged right to pointer");
                            Parser.verbose(memory.toString());
                	    } else if (line.equals(".. ..  .")) {
                            List<String> aft = new ArrayList<String>();
                            memory.add("tmp");
                            aft.add(String.join("",memory.subList(0,pointer+1)));
                            aft.addAll(memory.subList(pointer+1,memory.size()-1));
							memory.clear();
							memory.addAll(aft);
                            Parser.verbose("Merged left to pointer");
                            Parser.verbose(memory.toString());
	                    } else if (line.equals(".       ")) {
    	                    output.add(memory.get(pointer));
                            Parser.verbose("Pushed to memory: "+memory.get(pointer));
							memory.set(pointer,"");
                        } else if (line.equals(". .     ")) {
                            getc = false;
                            run = "add";
                            Parser.verbose("cmd: add");
                        } else if (line.equals(" . .    ")) {
                            getc = false;
                            run = "sub";
                            Parser.verbose("cmd: sub");
                        } else if (line.equals(". . .   ")) {
                            if (memory.size() <= pointer) {
                                Parser.error("Pointer outside of assigned memory");
                            }
                            int a = Integer.parseInt(memory.get(pointer));
                            int b = Integer.parseInt(memory.get(pointer+1));
                            memory.set(pointer,Integer.toString(a+b));
                        } else if (line.equals(" . . .  ")) {
                            if (memory.size() <= pointer) {
                                Parser.error("Pointer outside of assigned memory");
                            }
                            int a = Integer.parseInt(memory.get(pointer));
                            int b = Integer.parseInt(memory.get(pointer+1));
                            memory.set(pointer,Integer.toString(a-b));
                        } else if (line.equals(".    . .")) {
                            getc = false;
                            run = "for";
                            Parser.verbose("cmd: for");
                        } else {
                            Parser.warn("Unknown line on line "+indline+". Skipping. It may be useful, so check if it's written correctly:\n"+line);
                        }
                    } else {
                        if (run == "push") {
                            int tmp = Integer.parseInt(line.replace(".","1").replace(" ", "0"),2);
                            if (memory.size() <= pointer) {
                                memory.add(Integer.toString(tmp));
                            } else {
                                memory.set(pointer,Integer.toString(tmp));
                            }
                            Parser.verbose("pushed: "+tmp+" ("+tmp+")");
                            Parser.verbose(memory.toString());
                            
                            getc = true;
                            run = "";
                        } else if (run == "add") {
                            String tmp = line.replace(".","1").replace(" ", "0");
                            String ln = memory.get(pointer).replace(".","1").replace(" ","0");
                            memory.set(pointer,Integer.toString(Integer.parseInt(tmp,2)+Integer.parseInt(ln,2)));
                            Parser.verbose("Added "+Integer.parseInt(ln,2)+ " and "+Integer.parseInt(tmp,2)+" to "+pointer);
                            getc = true;
                            run = "";
                        } else if (run == "sub") {
                            String tmp = line.replace(".","1").replace(" ", "0");
                            String ln = memory.get(pointer).replace(".","1").replace(" ","0");
                            ln = ln.replace(" ", "0");
                            memory.set(pointer,Integer.toString(Integer.parseInt(ln,2)-Integer.parseInt(tmp,2)));
                            Parser.verbose("Subtracted "+tmp+ " and "+ln+" to "+pointer);
                            getc = true;
                            run = "";
                        } else if (run == "for") {
                            if (fFlag) {
                                formem.add(line);
                                Parser.verbose("Added to for memory: "+line);
                            } else {
                                fRep = Integer.parseInt(line.replace(".","1").replace(" ","0"),2);
                                fFlag = true;
                                Parser.verbose("For repeat: "+fRep);
                            }
                        }
                    }
                } else {
                    Parser.warn("Line is less than 8 characters and isn't a comment. Make sure you have all the spaces in.");
                }
            } else {
                if (run == "for") {
                    Parser p = new Parser();
                    Parser.verbose("For loop commands: "+formem);
                    for (int i = 0; i < fRep; i++) {
                        p.parse(formem,flagl);
                    }
                    getc = true;
                    run = "";
                    fFlag = false;
                    formem.clear();
                } else {
                    Parser.verbose("Comment found");
                }
            }   
        }
        return output;
    }
}