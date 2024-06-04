package tokenizers;

public class OccurenceStat<K, V> {
	 private final K key;
	    private final V value;

	    public OccurenceStat(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    public K getKey() {
	        return key;
	    }

	    public V getValue() {
	        return value;
	    }

	    @Override
	    public String toString() {
	        return "(" + key + ", " + value + ")";
	    }

}
