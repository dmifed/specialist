package multythreading.readwritelock;


// RR
// RW s
// WW s

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final Map<K,V> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public V get(K key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public void  put(K key, V value){
        writeLock.lock();
        try {
            map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

}
