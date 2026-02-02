package pl.edu.wat.wcy.cop.common.threads;

import java.util.Date;


// Provides a thread-safe single-value buffer for producer-consumer handoff.
public class SynchronizedBuffer<T> {

    private T obj;
    private boolean wasPut;
    private Date creationDate = new Date();

    public synchronized T take() {
        while (!wasPut) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return obj;
    }

    public synchronized void put(T obj) {
        this.obj = obj;
        wasPut = true;
        notifyAll();
    }

    public synchronized boolean isEnd() {
        return wasPut;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
