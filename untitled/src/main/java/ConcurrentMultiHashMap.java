import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ConcurrentMultiHashMap<K, V> {
    private ConcurrentHashMap<K, CopyOnWriteArrayList<V>> hashMap;

    public ConcurrentMultiHashMap() {
        hashMap = new ConcurrentHashMap<>();
    }


    public void putAll(K k, Collection<V> v) {
        Object value = hashMap.get(k);
        if (value == null) {
            hashMap.put(k, new CopyOnWriteArrayList<>(v));
        } else {
            CopyOnWriteArrayList<V> values = (CopyOnWriteArrayList<V>) value;
            values.addAll(v);
        }
    }

    public List<V> getValue(K k) {
        return hashMap.get(k);
    }

    public void removeAll(K k) {
        hashMap.remove(k);
    }

    public void remove(K k, V v) {
        List<V> vList = getValue(k);
        if (vList != null) vList.remove(v);
    }

    public void clear() {
        hashMap.clear();
    }

    public int size() {
        return hashMap.size();
    }
}
