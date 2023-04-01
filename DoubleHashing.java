public class DoubleHashing<T> extends Hashtable<T> {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    private int h1(Object key) {
        return positiveMod(key.hashCode(), capacity);
    }

    private int h2(Object key) {
        return 1 + positiveMod(key.hashCode(), capacity - 2);
    }

    @Override
    protected int hash(Object key, int probeCount) {
        return (h1(key) + probeCount * h2(key)) % capacity;
    }

}