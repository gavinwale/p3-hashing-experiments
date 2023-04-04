/*
 * Hashtable is an abstract class that lays the foundation for the
 *  further implementation of the hash table by LinearProbing.java
 *  and DoubleHashing.java.
 * 
 * @author gavinwale
 */

@SuppressWarnings("unchecked")
public abstract class Hashtable<T> {

    // Class variables, protected access for ease of use and for academia
    protected HashObject<T>[] table;
    protected int capacity;
    protected double loadFactor;
    protected int inserts = 0;
    protected int totalDupes;
    protected int totalProbes;
    protected int totalInserts;

    /*
     * Constructor for Hashtable
     * 
     * @param - int capacity (maximum size of hash table)
     * @param - double loadFactor (how many slots can be filled)
     */
    public Hashtable(int capacity, double loadFactor) {
        table = new HashObject[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.totalDupes = 0;
        this.totalProbes = 0;
        this.totalInserts = 0;
    }

    /*
     * Inserts a generic key into the hash table
     * 
     * @param - T key
     * @return - -1 if duplicate
     * @return - 1 if original
     */
    protected int insert(T key) {
        int probe = 0;
        // Find the index by hashing with the key and current probe (0)
        int index = hash(key, probe);
        // Java likes it if you make a new object first
        HashObject<T> insertObject = new HashObject<T>(key);
        while (table[index] != null) {
            if (table[index].equals(insertObject)) {
                totalDupes++;
                table[index].incrementDuplicateCount();
                return -1;
            } else if (!table[index].equals(insertObject)) {
                probe++;
                // DECIDE BETWEEN THESE
                table[index].incrementProbeCount();
                insertObject.incrementProbeCount();
                // Neither of them do anything important i guess :)
                index = hash(key,probe);
            }
        }
        // If the space is null, insert into empty spot
        table[index] = insertObject;
        // Important incrementations
        totalInserts++;
        totalProbes += probe + 1;
        return 1;
    }

    /*
     * Get method for the total number of inserted objects
     * 
     * @return - total number of duplicates and original inserts
     */
    protected int getTotalInserted() {
        return totalDupes + totalInserts;
    }

    /*
     * Abstract method definition, implemented in both DoubleHashing.java
     *  and LinearProbing.java based on their respective hashing algorithms
     */
    protected abstract int hash (Object element, int probe);

    /*
     * Get method for the current load factor
     * 
     * @return - the quotient of total original(non-duplicates) inserts and the capacity
     */
    protected double getCurrentLoadFactor() {
        return (double) totalInserts / (double) capacity;
    }

    /*
     * Method to ensure modulus is positive
     * 
     * @param - int dividend
     * @param - int divisor
     * 
     * @return - int quotient
     */
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }
}