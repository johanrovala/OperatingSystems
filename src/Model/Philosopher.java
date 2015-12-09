package Model;

import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by johanrovala on 27/11/15.
 */
public class Philosopher implements Runnable{

    // Chopsticks
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;

    PrintWriter myStatisticsFile;
    PrintWriter test = new PrintWriter("test.txt", "UTF-8");

    // Variables
    private Random randomNumb = new Random();
    private int philosopherNumber;
    private volatile boolean isHungry = false;
    private boolean isRunning = true;

    // Gui Object
    Circle circleObject;

    public Philosopher(int philosopherNumber, Chopstick leftChopstick, Chopstick rightChopstick, Circle circleObject) throws FileNotFoundException, UnsupportedEncodingException {
        myStatisticsFile = new PrintWriter("statistics.txt", "UTF-8");
        this.philosopherNumber = philosopherNumber;
        this.leftChopstick = leftChopstick;
        this.leftChopstick.setMyStatisticsFile(myStatisticsFile);
        this.rightChopstick = rightChopstick;
        this.rightChopstick.setMyStatisticsFile(myStatisticsFile);
        this.circleObject = circleObject;
    }


    @Override
    public void run() {
        while (isRunning) {
            try {
                while (isHungry) {
                    if (leftChopstick.isAvailable()) {
                        leftChopstick.pickUp(this, "left");
                        if(rightChopstick.isAvailable()){
                            rightChopstick.pickUp(this, "right");
                            eating();
                        }
                        leftChopstick.putDown(this, "left");
                        rightChopstick.putDown(this, "right");
                    }
                    Thread.sleep(500);
                }
                while (!isHungry) {
                    thinking();
                    hungry();
                  //  int i = randomNumb.nextInt(2000)+1000;
                  //  Thread.sleep(i);
                    break;
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void eating() throws InterruptedException {
        System.out.println(this.toString() + " is eating");
        myStatisticsFile.println(this.toString() + " is eating");
        test.println(this.toString() + " is eating");
        test.close();
        isHungry = false;
        this.circleObject.setFill(Paint.valueOf("#8b282a"));
        int i = randomNumb.nextInt(5000)+1000;
        Thread.sleep(i);
    }

    public void thinking() throws InterruptedException {
        System.out.println(this.toString() + " is thinking");
        myStatisticsFile.println(this.toString() + " is thinking");
        isHungry = true;
        this.circleObject.setFill(Paint.valueOf("#28e7b2"));
        int i = randomNumb.nextInt(5000)+1000;
        Thread.sleep(i);
    }

    public void hungry() throws InterruptedException{
        System.out.println(this.toString() + " is hungry");
        myStatisticsFile.println(this.toString() + " is hungry");
        this.circleObject.setFill(Paint.valueOf("#d7b3ff"));
    }

    public void kill(){
        isRunning = false;
    }

    @Override
    public String toString(){
        return "Philosopher number " + philosopherNumber;
    }
}
