package src.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import src.log.Log;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Thread responsible for storing data in files.
 */
public class Storage extends Thread {
    private Semaphore semaphore = new Semaphore(0);
    private static Storage instance;
    private boolean running = false;
    private ConcurrentHashMap<String, ObjectOutputStream> streams = new ConcurrentHashMap<String, ObjectOutputStream>();
    private ConcurrentHashMap<String, Integer> tasks = new ConcurrentHashMap<String, Integer>();
    private ConcurrentHashMap<String, LinkedList<Serializable>> map = new ConcurrentHashMap<String, LinkedList<Serializable>>(); 

    //#region Singleton
    private Storage() {
        super();
        setPriority(3);
        try {
            Files.createDirectories(Paths.get("data"));
        } catch (Exception e) {};
        this.start();
    };
    
    public static Storage getInstance() {
        if(Storage.instance == null) Storage.instance = new Storage();
        return Storage.instance;
    };
    //#endregion

    //#region Tasks
    /**
     * Verify if the thread is busy.
     * If not, start processing new tasks.
     */
    public void inspect() {
        if(this.semaphore.availablePermits() == 0) {
            this.semaphore.release();
        };
    };

    /**
     * Check if there is any task in queue.
     * @return true if there are tasks, false otherwise
     */
    public boolean hasTask() {
        return this.tasks.size() > 0 && this.tasks.values().stream().anyMatch((task) -> task != 0);
    };

    /**
     * Check if there is a specific named task in queue.
     * @param name task name
     * @return true if there are tasks, false otherwise
     */
    public boolean hasTask(String name) {
        return this.tasks.containsKey(name) && this.tasks.get(name) > 0;
    };
    //#endregion

    //#region Core
    /**
     * Request the storage of a list of serializable objects in a file.
     * @param name - the file name
     * @param data - the list
     */
    @SuppressWarnings("unchecked")
    protected synchronized <T extends Serializable> void store(String name, LinkedList<T> data) {
        try {
            boolean isFirstTask = !hasTask();
            this.tasks.merge(name, 1, Integer::sum);
            this.map.put(name, (LinkedList<Serializable>) data);
            if(isFirstTask) {
                Log.print("Storage", "Tasks were requested.");
                this.inspect();
            };
        } catch (Exception e) {
            Log.print("Storage", "Task request failed.");
        };
    };

    /**
     * Run the thread, storing the data in the files.
     */
    @Override
    public void run() {
        this.running = true;
        Log.print("Storage", "Started.");
        while(this.running || this.hasTask()) {
            try {
                // Wait for tasks
                if(!this.hasTask()) {
                    semaphore.acquire();
                };

                // Process tasks by names
                for(String name : this.map.keySet()) {
                    // Wait for more requests
                    if(this.hasTask(name)) Thread.sleep(1000);
                    else continue;

                    // Remove unnecessary tasks
                    Integer count = this.tasks.getOrDefault(name, 0);
                    if(count > 1) {
                        this.tasks.put(name, 1);
                        continue;
                    };
                    
                    // Get task data
                    LinkedList<Serializable> storable = this.map.get(name);
                    ObjectOutputStream object = this.streams.get(name);

                    if(object == null) {
                        FileOutputStream out = new FileOutputStream("data/" + name + ".dat");
                        object = new ObjectOutputStream(out);
                    };
                    
                    // Store the data and remove the task
                    object.writeObject(storable);
                    object.flush();
                    this.map.remove(name);
                    this.tasks.put(name, 0);
                    Log.print("Storage", "Task finished, " + storable.size() + " " + name + " were stored.");
                };
            } catch (Exception e) {
                Log.print("Storage", "Tasks failed.");
            };
        };

        // Close all streams
        for(String name : this.streams.keySet()) {
            try {
                ObjectOutputStream object = this.streams.get(name);
                object.close();
            } catch (Exception e) {
                Log.print("Storage", "Can't close stream of " + name + ".");
            };
        };

        Log.print("Storage", "Finished.");
    };
    //#endregion

    //#region Control
    /**
     * Finish the thread safely.
     */
    public static void finish() {
        try {
            if(Storage.instance != null) {
                Log.print("Storage", "Finishing...");
                Storage.instance.running = false;
                Storage.instance.inspect();
                Storage.instance.join();
            };
        } catch (InterruptedException e) {
            Log.print("Storage", "Can't finish safely.");
        };
    };

    /**
     * Start the thread.
     */
    @Override
    public void start() {
        try {
            Log.print("Storage", "Starting...");
            super.start();
        } catch (IllegalThreadStateException e) {
            Log.print("Storage", "Can't start.");
        };
    };
    //#endregion
};
