import java.io.File;
import java.util.*;


public class HashtableTest {

    public static void main(String[] args) throws Exception {

        //TwinPrimeGenerator tpg = new TwinPrimeGenerator();
        int highTwinPrime = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        int lowTwinPrime = highTwinPrime - 2;

        int debugLevel = 0;

        if (args.length == 3) debugLevel = Integer.parseInt(args[2]);

        if (debugLevel < 0 || debugLevel > 2) throw new IllegalArgumentException("Debug level must be between 0 and 2");

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
                break;

            default:
                break;
                
        }



    }

}