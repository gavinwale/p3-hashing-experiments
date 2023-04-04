/*
 * Generic HashObject class that defines the characteristics of what
 *  will be stored in the hash table
 * 
 * @author gavinwale
 */
@SuppressWarnings("unchecked")
public class HashObject<T> {

    // Class variables
    private T key;
    private int duplicateCount;
    private int probeCount;

    /*
     * Constructor
     * 
     * @param - T key
     */
    public HashObject(T key) {
        this.key = key;
        this.duplicateCount = 0;
        this.probeCount = 1;
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
    protected int getDuplicateCount() {
        return duplicateCount;
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
     * Sets the probeCount of the HashObject
     * 
     * @param - int i
     */
    protected void setProbeCount(int i) {
        probeCount = i;
    }

    /*
     * Increments the frequencyCount
     */
    protected void incrementDuplicateCount() {
        this.duplicateCount++;
    }

    /*
     * Increments the probeCount
     */
    protected void incrementProbeCount() {
        this.probeCount++;
    }

    @Override
    public String toString() {
        return key + " " + duplicateCount + " " + probeCount;
    }

    @Override
    public boolean equals(Object o) {
        HashObject<T> o2 = (HashObject<T>)o;
        return o2.key.equals(this.key);
    }

}
