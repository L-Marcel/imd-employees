package src.storage;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import src.log.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * Abstract class for objects that represent database tables and must be stored
 * after each operation on a map.
 * @param <T> the type of object to be stored
 */
public abstract class StorableMap<K extends Serializable, T extends Serializable> implements Serializable {
    private Storage storage = Storage.getInstance();
    private String name;
    private LinkedHashMap<K, T> instances;

    /**
     * Constructor of the class, loads the data from the file
     * associated with the name, if it exists.
     * @param name
    */
    @SuppressWarnings("unchecked")
    public StorableMap(String name) {
        this.name = name;
        try {
            FileInputStream input = new FileInputStream("data/" + this.name + ".dat");  
            ObjectInputStream object = new ObjectInputStream(input);
            this.instances = (LinkedHashMap<K, T>) object.readObject();
            Log.print("Storable", "Loadded " + this.instances.size() + " " + this.name + ".");
            object.close();         
        } catch (FileNotFoundException e) {
            Log.print("Storable", "Creating " + this.name + " database.");
            this.instances = new LinkedHashMap<K, T>();
        } catch (Exception e) {
            Log.print("Storable", "Failed on load " + this.name + ".");
            Log.print("Error", e.getMessage());
            Log.print("Storable", "Creating " + this.name + " database.");
            this.instances = new LinkedHashMap<K, T>();
        };
    };

 
    /**
     * Requests the storage of the updated instances in the file.
    */
    public void store() {
        storage.store(this.name, this.instances);
    };

    /**
     * Returns the map of stored instances.
     * @return the map
     */
    public LinkedHashMap<K, T> get() {
        return this.instances;
    };

    /**
     * Add an entry to the map and request storage.
     * @param k - the entry key
     * @param t - the entry value
     */
    public void put(K k, T t) {
        instances.put(k, t);
        store();
    };

    /**
     * Remove an instance from the map by the key and request storage.
     * @param k - the key
     */
    public void remove(K k) {
        instances.remove(k);
        store();
    };
};
