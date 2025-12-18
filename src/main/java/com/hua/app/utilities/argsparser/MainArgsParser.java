package com.hua.app.utilities.argsparser;

import java.util.ArrayList;

public class MainArgsParser {
    public static void parseArgs(String[] args, double weight, int age, String sex, ArrayList<String> totalFiles) {
        for (int i = 0; i < args.length; i++) {
            String currentArg = args[i];
            String argValue = null;
            
            if (currentArg.equals("-w")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    double value = Double.parseDouble(argValue);
                    weight = value;
                    if (weight < 0) {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Expected numerical value after '-w'");
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected positive or zero value after '-w'");
                    System.exit(1);
                }
            }
            
            if (currentArg.equals("-a")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    int value = Integer.parseInt(argValue);
                    age = value;
                    if (age < 0) {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Expected numerical value after '-a'");
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected positive or zero value  after '-a'");
                    System.exit(1);
                }
            }
            
            if (currentArg.equals("-s")) {
                if (i + 1 >= args.length) {
                    break;
                }
                
                argValue = args[i + 1];
                try {
                    if (argValue.matches("^[mM]$")) {
                        sex = "Male";
                    } else if (argValue.matches("^[fF]$")) {
                        sex = "Female";
                    } else {
                        throw new IllegalArgumentException();
                    }
                    i++;
                    continue;
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Expected 'm' (male) or 'f' (female) after '-s'");
                    System.exit(1);
                }
            }
            
            if (currentArg.endsWith(".tcx")) {
                totalFiles.addLast(currentArg);
            }
        }
    }
}