import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class ConcurrentMultiHashMap<K, V> {
    private ConcurrentHashMap<K, CopyOnWriteArrayList<V>> hashMap;
    private CopyOnWriteArrayList<V> values;

    public ConcurrentMultiHashMap() {
        hashMap = new ConcurrentHashMap<>();
        values = new CopyOnWriteArrayList<>();
    }

    public void put(K k, V v) {
        Object value = hashMap.get(k);
        if (value == null) {
            CopyOnWriteArrayList<V> temp = new CopyOnWriteArrayList<>();
            temp.add(v);
            hashMap.put(k, temp);
            values.add(v);
        } else {
            CopyOnWriteArrayList<V> temp = (CopyOnWriteArrayList<V>) value;
            temp.add(v);
            values.add(v);
        }
    }

    public void putAll(K k, Collection<V> v) {
        Object value = hashMap.get(k);
        if (value == null) {
            hashMap.put(k, new CopyOnWriteArrayList<>(v));
            values.addAll(v);
        } else {
            CopyOnWriteArrayList<V> temp = (CopyOnWriteArrayList<V>) value;
            temp.addAll(v);
            values.addAll(v);
        }
    }

    public Collection<V> get(K k) {
        return hashMap.get(k);
    }

    public void removeAll(K k) {
        values.removeAll(hashMap.remove(k));
    }

    public void remove(K k, V v) {
        Collection<V> vList = get(k);
        if (vList != null) {
            vList.remove(v);
            values.remove(v);
        }
    }

    public Collection<V> values() {
        return values;
    }

    public Set<K> keySet() {
        return hashMap.keySet();
    }

    public boolean contains(V v) {
        return values.contains(v);
    }

    public boolean containsKey(K k){
        return hashMap.containsKey(k);
    }

    public boolean isEmpty(){
        return hashMap.isEmpty();
    }

    public void clear() {
        hashMap.clear();
        values.clear();
    }

    public int size() {
        return hashMap.size();
    }
}
