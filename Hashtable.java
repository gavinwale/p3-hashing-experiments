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

    // public int insert(Object hashObject) {
    //     //HashObject userObject = new HashObject(hashObject);
    //     int i = 0;
    //     int index;

    //     while(i != capacity) {
    //         index = hash(hashObject, i); //calling hash function
    //         if(table[index] == null) { //inserting object if null
    //             i++;
    //             table[index] = hashObject;
    //             hashObject.incrementProbeCount(i);
    //             inserts++;
    //             totalProbes += i;
    //             return index;
    //         } else { //found duplicate
    //             if(table[index].equals(hashObject)){
    //                 table[index].incrementFrequencyCount();
    //                 totalDupes++;
    //                 return -1;
    //             }
    //             i++;
    //         }
    //     }
    //     return 0;
    // }



    protected int insert(HashObject key) {
        int probe = 1;
        int index = hash(key, probe);

        while (table[index] != null) {

            if (table[index].getKey().equals(key)) {
                totalDupes++;
                table[index].incrementFrequencyCount();
                return -1;
            } else if (!table[index].getKey().equals(key)) {
                totalProbes++;
                probe++;
                table[index].incrementProbeCount();
                index = hash(key,probe);
            }

        }

        table[index] = new HashObject(key);
        inserts++;
        return 1;
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