/*
 * Generic HashObject class that defines the characteristics of what
 *  will be stored in the hash table
 * 
 * @author gavinwale
 */
public class HashObject<T> {

    // Class variables
    private T key;
    private int frequencyCount;
    private int probeCount;

    /*
     * Constructor
     * 
     * @param - K key
     */
    public HashObject(T key) {
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    /*
     * Returns the key of the HashObject
     * 
     * @return - K key
     */
    protected T getKey() {
        return key;
    }

    /*
     * Returns the frequency of the HashObject
     * 
     * @return - int frequencyCount
     */
    protected int getFrequencyCount() {
        return frequencyCount;
    }

    /*
     * Returns the probeCount of the HashObject
     * 
     * @return - int probeCount
     */
    protected int getProbeCount() {
        return probeCount;
    }

    /*
     * Increments the frequencyCount
     */
    protected void incrementFrequencyCount() {
        this.frequencyCount++;
    }

    /*
     * Increments the probeCount
     */
    protected void incrementProbeCount() {
        this.probeCount++;
    }

    @Override
    public String toString() {
        return key + " " + (frequencyCount - 1) + " " + probeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof HashObject)) return false;
        HashObject<?> o2 = (HashObject<?>)o;
        return o2.key.equals(this.key);
    }
}
