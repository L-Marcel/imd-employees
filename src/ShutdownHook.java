package src;

import src.log.Log;
import src.storage.Storage;

/**
 * Thread responsible for exit the application safely.
 */
public class ShutdownHook extends Thread {
    @Override
    public void run() {
        System.out.println("\nEncerrando aplicação...");
        System.out.println("Sincronizando os dados salvos...");
        Storage.finish();
        Log.finish();
        System.out.println("Dados sincronizados com sucesso!");
    };
};
