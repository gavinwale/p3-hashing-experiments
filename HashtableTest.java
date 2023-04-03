import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class HashtableTest {

    public static void main(String[] args) throws Exception {

        int highTwinPrime = TwinPrimeGenerator.generateTwinPrime(95500, 96000);

        String inputType;

        int debugLevel = 0;

        if (args.length == 3) {
            debugLevel = Integer.parseInt(args[2]);
        }

        if (debugLevel < 0 || debugLevel > 2) {
            throw new IllegalArgumentException("Debug level must be between 0 and 2");
        }

        double loadFactor = Double.parseDouble(args[1]);

        switch(Integer.parseInt(args[0])) {

            // Random integers
            case 1:
                LinearProbing<Integer> linHashtable1 = new LinearProbing<Integer>(highTwinPrime, loadFactor);
                DoubleHashing<Integer> dbHashtable1 = new DoubleHashing<Integer>(highTwinPrime, loadFactor);
                Random rand = new Random();
                int randomNumber;

                int total1 = 0;
                while (linHashtable1.getCurrentLoadFactor() < loadFactor) {
                    randomNumber = rand.nextInt();
                    linHashtable1.insert(new HashObject<Integer>(randomNumber));
                    dbHashtable1.insert(new HashObject<Integer>(randomNumber));
                    total1++;
                }
                inputType = "Integer";
                if (debugLevel == 0) {
                    debug0(linHashtable1, dbHashtable1, inputType, total1);
                } else if (debugLevel == 1) {
                    String fileNameLin = ("linear-" + loadFactor);
                    String fileNameDbl = ("double-" + loadFactor);
                    debug1(linHashtable1, dbHashtable1, inputType, total1);
                    dumpToFile(fileNameLin, linHashtable1);
                    dumpToFile(fileNameDbl, dbHashtable1);
                }
                break;

            // Random longs
            case 2:
                LinearProbing<Date> linHashtable2 = new LinearProbing<Date>(highTwinPrime, loadFactor);
                DoubleHashing<Date> dbHashtable2 = new DoubleHashing<Date>(highTwinPrime, loadFactor);
                long current = new Date().getTime();

                int total2 = 0;
                while (linHashtable2.getCurrentLoadFactor() < loadFactor) {
                    current += 1000;
                    Date date = new Date(current);
                    linHashtable2.insert(new HashObject<Date>(date));
                    dbHashtable2.insert(new HashObject<Date>(date));
                    total2++;
                }
                inputType = "Long";

                if (debugLevel == 0) {
                    debug0(linHashtable2, dbHashtable2, inputType, total2);
                } else if (debugLevel == 1) {
                    String fileNameLin = ("linear-" + loadFactor);
                    String fileNameDbl = ("double-" + loadFactor);
                    debug1(linHashtable2, dbHashtable2, inputType, total2);
                    dumpToFile(fileNameLin, linHashtable2);
                    dumpToFile(fileNameDbl, dbHashtable2);
                }

                
                break;

            // List of words from hard-coded input file
            case 3:
                LinearProbing<String> linHashtable3 = new LinearProbing<String>(highTwinPrime, loadFactor);
                DoubleHashing<String> dbHashtable3 = new DoubleHashing<String>(highTwinPrime, loadFactor);
                // File file = new File("word-test-list");
                // Scanner scan = new Scanner(file);
                // String nextWord;

                // while (linHashtable3.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {
                //     nextWord = scan.nextLine();
                //     linHashtable3.insert(new HashObject<String>(nextWord));
                //     dbHashtable3.insert(new HashObject<String>(nextWord));
                // }

                int total3 = 0;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("word-list"));

                    String newWord;

                    while (((newWord = reader.readLine()) != null) && linHashtable3.getCurrentLoadFactor() < loadFactor) {
                        linHashtable3.insert(new HashObject<String>(newWord));
                        dbHashtable3.insert(new HashObject<String>(newWord));
                        total3++;
                    }

                    reader.close();
                } catch(FileNotFoundException e) {
                    System.out.println("File not found");
                }

                
                inputType = "Word-List";
                if (debugLevel == 0) {
                    
                    debug0(linHashtable3, dbHashtable3, inputType, total3);
                } else if (debugLevel == 1) {
                    
                    String fileNameLin = ("linear-" + loadFactor);
                    String fileNameDbl = ("double-" + loadFactor);
                    debug1(linHashtable3, dbHashtable3, inputType, total3);
                    dumpToFile(fileNameLin, linHashtable3);
                    dumpToFile(fileNameDbl, dbHashtable3);
                }
                break;

            default:
                break;

        }

    }

    public static String usage() {
        return "Usage:\njava HashtableTest <dataSource> <loadFactor> [<debugLevel>]";
    }

    public static void debug0(Hashtable<?> linear, Hashtable<?> dbl, String inputType, int total) {
        double avgLinProbes = 1 + (double) linear.totalProbes / (double) linear.inserts;
        double avgDblProbes = 1 + (double) dbl.totalProbes / (double) dbl.inserts;
        final DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("HashtableTest: Found a twin prime for table capacity: " + linear.capacity);
        System.out.println("HashtableTest: Input: " + inputType + "   Loadfactor: " + df.format(linear.loadFactor));
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableTest: size of hash table is " + linear.inserts);
        System.out.println("\tInserted " + total + " elements, of which " + linear.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + df.format(avgLinProbes));
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableTest: size of hash table is " + dbl.inserts);
        System.out.println("\tInserted " + dbl.getTotalInserted() + " elements, of which " + dbl.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + df.format(avgDblProbes));
    }

    public static void debug1(Hashtable<?> linear, Hashtable<?> dbl, String inputType, int total) {
        double avgLinProbes = 1 + (double) linear.totalProbes / (double) linear.inserts;
        double avgDblProbes = 1 + (double) dbl.totalProbes / (double) dbl.inserts;
        final DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("HashtableTest: Found a twin prime for table capacity: " + linear.capacity);
        System.out.println("HashtableTest: Input: " + inputType + "   Loadfactor: " + df.format(linear.loadFactor));
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableTest: size of hash table is " + linear.inserts);
        System.out.println("\tInserted " + total + " elements, of which " + linear.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + df.format(avgLinProbes));
        System.out.println("HashtableTest: Saved dump of hash table");
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableTest: size of hash table is " + dbl.inserts);
        System.out.println("\tInserted " + dbl.getTotalInserted() + " elements, of which " + dbl.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + df.format(avgDblProbes));
        System.out.println("HashtableTest: Saved dump of hash table");
    }

    public static void dumpToFile(String fileName, Hashtable<?> hashtable) {
        PrintWriter out;
        try {
            out = new PrintWriter(fileName);
            for (int i = 0; i <= hashtable.inserts; i++) {
                if (hashtable.table[i] != null) {
                    out.println(hashtable.table[i].toString());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}