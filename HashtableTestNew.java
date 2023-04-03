import java.io.File;
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
public class HashtableTestNew {
    
    /*
     * Main method that runs the experiments and takes user input from command line arguments
     * 
     * @usage - java HashtableTest <dataSource> <loadFactor> [<debugLevel>]
     */
    public static void main(String[] args) throws Exception {

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
        int debugLevel = 0;
        if (args.length == 3) {
            debugLevel = Integer.parseInt(args[2]);
        }
        if (debugLevel < 0 || debugLevel > 2) {
            printUsage();
            throw new IllegalArgumentException("Debug level must be between 0 and 2");
        }


        ///////////////////// EXPERIMENT ///////////////////////////////

        if (inputTypeNum == 1) {
            // DO STUFF FOR INTEGERS
            LinearProbing<Integer> linearTable = new LinearProbing<Integer>(capacity, loadFactor);
            DoubleHashing<Integer> doubleTable = new DoubleHashing<Integer>(capacity, loadFactor);
            Random rand = new Random();
            int totalLoops = 0;

            for (int i = 0; linearTable.getCurrentLoadFactor() < loadFactor; i++)  {
                linearTable.insert(new HashObject<Integer>(rand.nextInt()));
                doubleTable.insert(new HashObject<Integer>(rand.nextInt()));

                totalLoops = i;
            }

            debugZero(linearTable, doubleTable, inputTypeStr, totalLoops);

        } else if (inputTypeNum == 2) {
            // DO STUFF FOR LONGS
            LinearProbing<Long> linearTable = new LinearProbing<Long>(capacity, loadFactor);
            DoubleHashing<Long> doubleTable = new DoubleHashing<Long>(capacity, loadFactor);
            long current = new Date().getTime();
            int totalLoops = 0;

            for (int i = 0; linearTable.getCurrentLoadFactor() < loadFactor; i++) {
                current += 1000;
                linearTable.insert(new HashObject<Long>(current));
                doubleTable.insert(new HashObject<Long>(current));

                totalLoops = i;
            }

        } else {
            // DO STUFF FOR STRINGS
            LinearProbing<String> linearTable = new LinearProbing<String>(capacity, loadFactor);
            DoubleHashing<String> doubleTable = new DoubleHashing<String>(capacity, loadFactor);
            int totalLoops = 0;

            File file = new File("word-list");
            Scanner scan = new Scanner(file);
            String word;
            
            while(linearTable.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {

                word = scan.nextLine();

                linearTable.insert(new HashObject<String>(word));
                doubleTable.insert(new HashObject<String>(word));

                totalLoops++;
            }

            scan.close();

            debugZero(linearTable, doubleTable, inputTypeStr, totalLoops);
        }

    }

    public static void printUsage() {
        System.out.println("usage - java HashtableTest <dataSource> <loadFactor> [<debugLevel>]");
    }

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


}
