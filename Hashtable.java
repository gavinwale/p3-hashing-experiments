/*
 * Hashtable is an abstract class that lays the foundation for the
 *  further implementation of the hash table by LinearProbing.java
 *  and DoubleHashing.java.
 * 
 * @author gavinwale
 */

@SuppressWarnings("unchecked")
public abstract class Hashtable<T> {

    protected HashObject<T>[] table;
    protected int capacity;
    protected double loadFactor;
    protected int inserts = 0;
    protected int totalDupes;
    protected int totalProbes;
    protected int totalInserts;

    public Hashtable(int capacity, double loadFactor) {
        table = new HashObject[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.totalDupes = 0;
        this.totalProbes = 0;
        this.totalInserts = 0;
    }

    /*
     * Inserts a generic HashObject into the hash table
     * 
     * @param - HashObject<T> key
     */
    // public void insert(HashObject<T> key) {

    //     // Probe starts at zero, is included in the hashing, increments when probed
    //     int probe = 0;
    //     // First index for the key to try to be inserted at
    //     int index = hash(key, probe);

    //     // We can probe as many times as there are slots in the hash table
    //     while (probe < capacity) {

    //         // If there is an empty slot in the hash table
    //         if (table[index] == null) {
    //             // Set the slot to the key
    //             table[index] = key;
    //             // Increment the number of true inserts
    //             totalInserts++;
    //             // Add the number of probes this took to the totalProbes variable
    //             totalProbes += probe + 1;
    //             // Set the probeCount of the element in the table at index
    //             table[index].setProbeCount(probe + 1);
    //             // Break out of the loop and method
    //             return;

    //         // If the slot in the hash table at index equals what we are trying to insert
    //         } else if (table[index].equals(key)) {
    //             // It is a duplicate, increment the total number of duplicates
    //             totalDupes++;

    //             totalProbes += probe + 1;

    //             // Increment the duplicate count at the specific index
    //             table[index].incrementDuplicateCount();
    //             // Break out of the loop and method
    //             return;
    //         }

    //         // If there is not an empty slot in the table or there is not a duplicate,
    //         //  that means the slot is taken already and we must probe to find the next
    //         //  available slot in the hash table.
    //         probe++;
    //     }
    // }

    /*
     * Return -1 if duplicate
     * Return 1 if original
     */
    protected void insert(T key) {
        int probe = 0;
        int index = hash(key, probe);
        HashObject<T> insertObject = new HashObject<T>(key);
        while (table[index] != null) {
            if (table[index].equals(insertObject)) {
                totalDupes++;
                table[index].incrementDuplicateCount();
                return;
            } else if (!table[index].equals(insertObject)) {
                //totalProbes++;
                probe++;
                // DECIDE BETWEEN THESE
                table[index].incrementProbeCount();
                insertObject.incrementProbeCount();
                index = hash(key,probe);
            }
        }
        table[index] = insertObject;
        totalInserts++;
        totalProbes += probe + 1;
        return;
    }

    protected int getTotalInserted() {
        return totalDupes + totalInserts;
    }

    protected abstract int hash (Object element, int probe);

    protected double getCurrentLoadFactor() {
        return (double) totalInserts / (double) capacity;
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }
}