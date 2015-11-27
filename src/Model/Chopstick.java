package Model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by johanrovala on 27/11/15.
 */
public class Chopstick {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private boolean isAvailable = true;

    public void pickUp(Philosopher chosenOne, String leftOrRight){
        reentrantLock.lock();
        System.out.println(chosenOne + " picked up " + leftOrRight + " chopstick");
        isAvailable = false;
    }

    public void putDown(Philosopher chosenOne, String leftOrRight){
        reentrantLock.unlock();
        System.out.println(chosenOne + " put down " + leftOrRight + " chopstick");
        isAvailable = true;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

}
