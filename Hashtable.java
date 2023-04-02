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

        // If the spot in the table has something ANDAND what is in the table does not equal what we are trying to insert
        while (table[index] != null) {

            if (!table[index].getKey().equals(key)) {
                probe++;
                index = hash(key, probe);
            } else if (table[index].getKey().equals(key)) {
                totalDupes++;
            }
        }

        table[index] = new HashObject(key);
        inserts++;

        // if (table[index] == null) {
            
        // } else {
        //     table[index].incrementFrequencyCount();
        //     totalDupes++;
        // }
        // inserts++;
        // totalProbes += probe;
    }

    protected int getTotalInserted() {
        return totalDupes + inserts;
    }

    protected abstract int hash (Object element, int probe);

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