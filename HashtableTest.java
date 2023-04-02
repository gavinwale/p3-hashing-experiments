import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

            case 1:
                LinearProbing<Integer> linHashtable1 = new LinearProbing<Integer>(highTwinPrime, loadFactor);
                DoubleHashing<Integer> dbHashtable1 = new DoubleHashing<Integer>(highTwinPrime, loadFactor);
                Random rand = new Random();
                int randomNumber;

                while (linHashtable1.getCurrentLoadFactor() < loadFactor) {
                    randomNumber = rand.nextInt();
                    linHashtable1.insert(new HashObject<Integer>(randomNumber));
                    dbHashtable1.insert(new HashObject<Integer>(randomNumber));
                }
                inputType = "Integer";
                debug0(linHashtable1, dbHashtable1, inputType);
                break;
            case 2:
                LinearProbing<Long> linHashtable2 = new LinearProbing<Long>(highTwinPrime, loadFactor);
                DoubleHashing<Long> dbHashtable2 = new DoubleHashing<Long>(highTwinPrime, loadFactor);
                long current = new Date().getTime();

                while (linHashtable2.getCurrentLoadFactor() < loadFactor) {
                    current += 1000;
                    Date date = new Date(current);
                    linHashtable2.insert(new HashObject<Date>(date));
                    dbHashtable2.insert(new HashObject<Date>(date));
                }
                inputType = "Long";
                debug0(linHashtable2, dbHashtable2, inputType);
                break;
            case 3:
                LinearProbing<String> linHashtable3 = new LinearProbing<String>(highTwinPrime, loadFactor);
                DoubleHashing<String> dbHashtable3 = new DoubleHashing<String>(highTwinPrime, loadFactor);
                File file = new File("word-list");
                Scanner scan = new Scanner(file);
                String nextWord;

                while (linHashtable3.getCurrentLoadFactor() < loadFactor && scan.hasNextLine()) {
                    nextWord = scan.nextLine();
                    linHashtable3.insert(new HashObject<String>(nextWord));
                    dbHashtable3.insert(new HashObject<String>(nextWord));
                }
                inputType = "Word-List";
                debug0(linHashtable3, dbHashtable3, inputType);
                break;

            default:
                break;

        }

    }

    public static String usage() {
        return "Usage:\njava HashtableTest <dataSource> <loadFactor> [<debugLevel>]";
    }

    public static void debug0(Hashtable<?> linear, Hashtable<?> dbl, String inputType) {
        double avgLinProbes = (double) linear.totalProbes / (double) linear.inserts;
        double avgDblProbes = (double) dbl.totalProbes / (double) dbl.inserts;

        System.out.println("HashtableTest: Found a twin prime for table capacity: " + linear.capacity);
        System.out.println("HashtableTest: Input: " + inputType + "   Loadfactor: " + linear.loadFactor);
        System.out.println("\tUsing Linear Probing");
        System.out.println("HashtableTest: size of hash table is " + linear.size);
        System.out.println("\tInserted " + linear.inserts + " elements, of which " + linear.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + avgLinProbes);
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        System.out.println("HashtableTest: size of hash table is " + dbl.size);
        System.out.println("\tInserted " + dbl.inserts + " elements, of which " + dbl.totalDupes + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + avgDblProbes);
    }

    public void dumpToFile(String fileName, Hashtable<?> hashtable) {
        PrintWriter out;
        try {
            out = new PrintWriter(fileName);

            for (int i = 0; i <= hashtable.size(); i++) {

                out.println(hashtable.table[i].toString());

            }



            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        


    }

}