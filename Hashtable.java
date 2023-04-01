@SuppressWarnings("unchecked")
public abstract class Hashtable<T> {

    protected int capacity;
    protected HashObject<T>[] table;
    protected int size;
    protected double loadFactor;


    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new HashObject[capacity];
        this.size = 0;
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
        }
    }

    protected HashObject<T> search(Object key) {
        int probe = 0;
        int index = hash(key, probe);
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index];
            }
            probe++;
            index = hash(key, probe);
        }
        return null;
    }

    protected abstract int hash (Object element, int probe);

    public HashObject<T> remove(Object key) {
        int probeCount = 0;
        int index = hash(key, probeCount);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            probeCount++;
            index = hash(key, probeCount);
        }
        if (table[index] != null) {
            HashObject<T> removed = table[index];
            table[index] = null;
            size--;
            return removed;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    

    
}