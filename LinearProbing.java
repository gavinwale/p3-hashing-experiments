public class LinearProbing<T> extends Hashtable<T> {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    protected int hash(Object element, int probe) {
        return positiveMod((element.hashCode() + probe), capacity);
    }

}