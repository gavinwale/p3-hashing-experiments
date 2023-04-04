/*
 * DoubleHashing implements the defined method "hash" from Hashtable.
 *  It uses two private methods h1 and h2 to compute the double
 *  hash of a given key.
 * 
 * @author gavinwale
 */
public class DoubleHashing<T> extends Hashtable<T> {

    /*
     * Constructor
     * 
     * @param - int capacity
     * @param - double loadFactor
     */
    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    /*
     * Helper calculation method that uses positiveMod
     * 
     * @param - Object key
     * @return - positiveMod of hashCode value of the key and the higher twin prime
     */
    private int h1(Object key) {
        return positiveMod(key.hashCode(), capacity);
    }

    /*
     * Helper calculation method that uses positiveMod
     * 
     * @param - Object key
     * @return - 1 + positiveMod of hashCode value of the key and the lower twin prime
     */
    private int h2(Object key) {
        return 1 + positiveMod(key.hashCode(), capacity - 2);
    }

    @Override
    protected int hash(Object key, int probeCount) {
        return (h1(key) + probeCount * h2(key)) % capacity;
    }

}