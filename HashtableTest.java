import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
/*
 * This class contains the main method that runs the experiments on the hash tables
 *  that have been built. Based on command line arguments, the main method inserts
 *  either a collection of random ints, longs, or words from a hard-coded text file.
 *  The insertions are done using both linear probing and double hashing. When the
 *  program has completed running, differentiation is made between the two methods.
 * 
 * @author gavinwale
 */
public class HashtableTest {
    
    /*
     * Main method that runs the experiments and takes user input from command line arguments
     * 
     * @usage - java HashtableTest <dataSource> <loadFactor> [<debugLevel>]
     */
    public static void main(String[] args) throws Exception {

        // Static call to TwinPrimeGenerator to find capacity of the hash tables
        int capacity = TwinPrimeGenerator.generateTwinPrime(95500, 96000);

        ///////////////////////// TYPE ///////////////////////////////////
        int inputTypeNum = Integer.parseInt(args[0]);
        String inputTypeStr = "";
        if (inputTypeNum == 1) {
            inputTypeStr = "Integer";
        } else  if (inputTypeNum == 2) {
            inputTypeStr = "Long";
        } else if (inputTypeNum == 3) {
            inputTypeStr = "Word-List";
        } else {
            printUsage();
            throw  new IllegalArgumentException("Input  type must be between 1 and 3");
        }

        ///////////////////// LOAD FACTOR ///////////////////////////////
        double loadFactor = Double.parseDouble(args[1]);

        ///////////////////// DEBUG LEVEL ///////////////////////////////
        // Set to 0 in case not enough arguments
        int debugLevel = 0;
        if (args.length == 3) {
            debugLevel = Integer.parseInt(args[2]);
        }
        if (debugLevel < 0 || debugLevel > 2) {
            printUsage();
            throw new IllegalArgumentException("Debug level must be between 0 and 2");
        }

        ////////////////////////////////////////////////////////////////
        ///////////////////// EXPERIMENT ///////////////////////////////
        ////////////////////////////////////////////////////////////////

        ///////////////////// INTEGERS ///////////////////////////////
        if (inputTypeNum == 1) {
            LinearProbing<Integer> linearTable = new LinearProbing<Integer>(capacity, loadFactor);
            DoubleHashing<Integer> doubleTable = new DoubleHashing<Integer>(capacity, loadFactor);
            Random rand = new Random();
            int totalLoops = 0;
            int number = 0;

            if (debugLevel == 0) {
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    number = rand.nextInt();
                    linearTable.insert(number);
                    doubleTable.insert(number);
                    totalLoops++;
                }
                debugZero(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else if (debugLevel == 1) {
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    number = rand.nextInt();
                    linearTable.insert(number);
                    doubleTable.insert(number);
                    totalLoops++;
                }
                dumpToFile("linear-dump.txt", linearTable);
                dumpToFile("double-dump.txt", doubleTable);
                debugOne(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else {
                int result = 0;
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    number = rand.nextInt();
                    result = linearTable.insert(number);
                    doubleTable.insert(number);
                    totalLoops++;
                    if (result > 0) {
                        System.out.println(number + " --- " + "ORIGINAL");
                    } else {
                        System.out.println(number + " --- " + "DUPLICATE");
                    }
                }
            }
        ///////////////////// LONGS ///////////////////////////////
        } else if (inputTypeNum == 2) {
            LinearProbing<Long> linearTable = new LinearProbing<Long>(capacity, loadFactor);
            DoubleHashing<Long> doubleTable = new DoubleHashing<Long>(capacity, loadFactor);
            long current = new Date().getTime();
            int totalLoops = 0;

            if (debugLevel == 0) {
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    current += 1000;
                    linearTable.insert(current);
                    doubleTable.insert(current);
                    totalLoops++;
                }
                debugZero(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else if (debugLevel == 1) {
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    current += 1000;
                    linearTable.insert(current);
                    doubleTable.insert(current);
                    totalLoops++;
                }
                dumpToFile("linear-dump.txt", linearTable);
                dumpToFile("double-dump.txt", doubleTable);
                debugOne(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else {
                int result = 0;
                while(linearTable.getCurrentLoadFactor() < loadFactor) {
                    current += 1000;
                    result = linearTable.insert(current);
                    doubleTable.insert(current);
                    totalLoops++;

                    if (result > 0) {
                        System.out.println(current + " --- " + "ORIGINAL");
                    } else {
                        System.out.println(current + " --- " + "DUPLICATE");
                    }
                }
            }
        ///////////////////// STRINGS ///////////////////////////////
        } else {
            LinearProbing<String> linearTable = new LinearProbing<String>(capacity, loadFactor);
            DoubleHashing<String> doubleTable = new DoubleHashing<String>(capacity, loadFactor);
            int totalLoops = 0;
            File file = new File("word-list");
            Scanner scan = new Scanner(file);
            String word;
    
            if (debugLevel == 0) {
                while(linearTable.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {
                    word = scan.nextLine();
                    linearTable.insert(word);
                    doubleTable.insert(word);
                    totalLoops++;
                }
                debugZero(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else if (debugLevel == 1) {
                while(linearTable.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {
                    word = scan.nextLine();
                    linearTable.insert(word);
                    doubleTable.insert(word);
                    totalLoops++;
                }
                dumpToFile("linear-dump.txt", linearTable);
                dumpToFile("double-dump.txt", doubleTable);
                debugOne(linearTable, doubleTable, inputTypeStr, totalLoops);
            } else {
                int result = 0;
                while(linearTable.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {
                    word = scan.nextLine();
                    result = linearTable.insert(word);
                    doubleTable.insert(word);
                    totalLoops++;

                    if (result > 0) {
                        System.out.println(word + " --- " + "ORIGINAL");
                    } else {
                        System.out.println(word + " --- " + "DUPLICATE");
                    }
                }
            }
            scan.close();
        }
    }

    /*
     * Void method that prints usage of the program
     */
    public static void printUsage() {
        System.out.println("usage - java HashtableTest <dataSource> <loadFactor> [<debugLevel>]");
    }

    /*
     * Void method that prints the required output for a level 0 debug. Includes information regarding
     *  capacity, size, total inserted elements, and duplicates. Prints to terminal.
     * 
     * @param - Hashtable<?> linearTable - a linear probing Hashtable of unbounded generics
     * @param - Hashtable<?> doubleTable - a double hashing Hashtable of unbounded generics
     * @param - String inType - the type that the hash tables are storing (not really unbounded)
     * @param - int loops - incremented value that increments for every iteration of the insert loop
     */
    public static void debugZero(Hashtable<?> linearTable, Hashtable<?> doubleTable, String inType, int loops) {
        double avgLinearProbes = (double) linearTable.totalProbes / (double) linearTable.totalInserts;
        double avgDoubleProbes = (double) doubleTable.totalProbes / (double) doubleTable.totalInserts;
        final DecimalFormat format = new DecimalFormat("0.00");

        System.out.println("HashtableTest: Found a twin prime for table capacity: " + linearTable.capacity);
        System.out.println("HashtableTest: Input: " + inType + "   Loadfactor: " + format.format(linearTable.loadFactor));
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableTest: size of hash table is " + linearTable.totalInserts);
        System.out.println("\tInserted " + loops + " elements, of which " + linearTable.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format(avgLinearProbes));
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableTest: size of hash table is " + doubleTable.totalInserts);
        System.out.println("\tInserted " + loops + " elements, of which " + doubleTable.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format(avgDoubleProbes));
    }

    /*
     * Void method that prints the required output for a level 1 debug. Includes information regarding
     *  capacity, size, total inserted elements, and duplicates. Prints to terminal.
     * 
     * Identical to debugZero with exception of 2 lines that notify the user that the hash table entries
     *  have been written to a file in the current directory.
     * 
     * @param - Hashtable<?> linearTable - a linear probing Hashtable of unbounded generics
     * @param - Hashtable<?> doubleTable - a double hashing Hashtable of unbounded generics
     * @param - String inType - the type that the hash tables are storing (not really unbounded)
     * @param - int loops - incremented value that increments for every iteration of the insert loop
     */
    public static void debugOne(Hashtable<?> linearTable, Hashtable<?> doubleTable, String inType, int loops) {
        double avgLinearProbes = (double) linearTable.totalProbes / (double) linearTable.totalInserts;
        double avgDoubleProbes = (double) doubleTable.totalProbes / (double) doubleTable.totalInserts;
        final DecimalFormat format = new DecimalFormat("0.00");

        System.out.println("HashtableTest: Found a twin prime for table capacity: " + linearTable.capacity);
        System.out.println("HashtableTest: Input: " + inType + "   Loadfactor: " + format.format(linearTable.loadFactor));
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableTest: size of hash table is " + linearTable.totalInserts);
        System.out.println("\tInserted " + loops + " elements, of which " + linearTable.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format(avgLinearProbes));
        System.out.println("HashtableTest: Saved dump of hash table");
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableTest: size of hash table is " + doubleTable.totalInserts);
        System.out.println("\tInserted " + loops + " elements, of which " + doubleTable.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + format.format(avgDoubleProbes));
        System.out.println("HashtableTest: Saved dump of hash table");
    }

    /*
     * Void helper method that uses Java's PrintWriter to write each insert of the given hash table
     *  to a file using the HashObject's toString() method.
     * 
     * @param - String fileName (the file to write to)
     * @param - Hashtable<?> table - a Hashtable of unbounded generics
     */
    public static void dumpToFile(String fileName, Hashtable<?> table) {
        PrintWriter out;
        try {
            out = new PrintWriter(fileName);
            for (int i = 0; i <= table.totalInserts; i++) {
                if (table.table[i] != null) {
                    out.println("table[" + i + "]: " + table.table[i].toString());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
