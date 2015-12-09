package Model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by johanrovala on 27/11/15.
 */
public class Chopstick {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private boolean isAvailable = true;
    Rectangle chopstickObject;

    PrintWriter myStatisticsFile;

    public Chopstick(Rectangle chopstickObject){
        this.chopstickObject = chopstickObject;
    }


    public void pickUp(Philosopher chosenOne, String leftOrRight) throws InterruptedException {
        reentrantLock.lock();
        this.chopstickObject.setFill(Paint.valueOf("#8b282a"));
        System.out.println(chosenOne + " picked up " + leftOrRight + " chopstick");
        myStatisticsFile.println(chosenOne + " picked up " + leftOrRight + " chopstick");
        isAvailable = false;
    }

    public void putDown(Philosopher chosenOne, String leftOrRight) throws InterruptedException {
        if(reentrantLock.isHeldByCurrentThread()){
            reentrantLock.unlock();
            this.chopstickObject.setFill(Paint.valueOf("#28e7b2"));
            System.out.println(chosenOne + " put down " + leftOrRight + " chopstick");
            myStatisticsFile.println(chosenOne + " put down " + leftOrRight + " chopstick");
            isAvailable = true;
        }
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setMyStatisticsFile(PrintWriter myStatisticsFile){
        this.myStatisticsFile = myStatisticsFile;
    }

}
