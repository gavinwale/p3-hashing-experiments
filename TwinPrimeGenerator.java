/*
 * This helper class provides a public method to generate twin primes
 *   between an upper and lower limit (min, max).
 * 
 * @author gavinwale
 */
public class TwinPrimeGenerator {

    /*
     * Finds the lowest set of twin primes between user input
     *  lower and upper limits
     * 
     * @param - int min, int max
     * @return - int (the higher of the two twin primes)
     */
    public static int generateTwinPrime(int min, int max) {
        // Loop from min to max - 2, if max was lower prime of the twins, it would not be within given bounds
        for (int i = min; i <= max - 2; i++) {
            // Check if the current "i" is prime AND the current "i + 2" is prime
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2;
            }
        }
        // If no twin primes were found, throw an IllegalArgumentException to tell the user they need to provide new bounds
        throw new IllegalArgumentException("No twin primes in the given range [min, max]");
    }

    /*
     * Helper method for generateTwinPrime that returns whether
     *  the parameter value is prime
     * 
     * @param - int num
     * @return - bool(true = prime, false = not prime)
     */
    private static boolean isPrime(int num) {

        // If the number is 1, 0, or negative, not prime.
        if (num <= 1) {
            return false;
        }

        // If the number is even, not prime
        if (num % 2 == 0) {
            return false;
        }

        // Starting at 3, (being divisible by 1 is inevitable and
        // being divisible by 2 has been checked) loop up to sqrt
        // of the given value. Checking past sqrt of num is redundant.
        // By cutting this in half we reduce operations to n/2
        for (int i = 3; i <= Math.sqrt(num); i++) {
            // If the number is divisble by i, not prime
            if (num % i == 0) {
                return false;
            }
        }
        // Gets through everything, is prime!
        return true;
    }
}
