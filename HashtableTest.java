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
                LinearProbing<Integer> rnHashtable = new LinearProbing<>(highTwinPrime, loadFactor);
                DoubleHashing<Integer> dbHashtable = new DoubleHashing<>(highTwinPrime, loadFactor);


                
                


        }



    }

}