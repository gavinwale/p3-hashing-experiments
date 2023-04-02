@SuppressWarnings("unchecked")
public abstract class Hashtable<T> {

    protected int capacity;
    protected HashObject<T>[] table;
    protected double loadFactor;
    protected int inserts = 0;
    protected int totalDupes = 0;
    protected int totalProbes = 0;
    protected int totalObjects = 0;


    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new HashObject[capacity];
    }

    protected void insert(Object key) {
        int probe = 0;
        int index = hash(key, probe);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            probe++;
            index = hash(key, probe);
        }
        if (table[index] == null) {
            table[index] = new HashObject(key);
        } else {
            table[index].incrementFrequencyCount();
            totalDupes++;
        }
        inserts++;
        totalProbes += probe;
    }

    // protected HashObject<T> search(Object key) {
    //     int probe = 0;
    //     int index = hash(key, probe);
    //     while (table[index] != null) {
    //         if (table[index].getKey().equals(key)) {
    //             return table[index];
    //         }
    //         probe++;
    //         index = hash(key, probe);
    //     }
    //     return null;
    // }

    protected abstract int hash (Object element, int probe);

    // public HashObject<T> remove(Object key) {
    //     int probeCount = 0;
    //     int index = hash(key, probeCount);
    //     while (table[index] != null && !table[index].getKey().equals(key)) {
    //         probeCount++;
    //         index = hash(key, probeCount);
    //     }
    //     if (table[index] != null) {
    //         HashObject<T> removed = table[index];
    //         table[index] = null;
    //         size--;
    //         return removed;
    //     }
    //     return null;
    // }

    protected double getCurrentLoadFactor() {
        return (double) inserts / (double) capacity;
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    

    
}