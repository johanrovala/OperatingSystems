package Model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by johanrovala on 27/11/15.
 */
public class Philosopher implements Runnable{

    // Chopsticks
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;

    // Variables
    private Random randomNumb = new Random();
    private int philosopherNumber;
    private volatile boolean isHungry = false;
    private boolean isRunning = true;

    // Time tracking variables
    private double averageThinkingTime = 0;
    private double averageEatingTime = 0;
    private double averageHungryTime = 0;


    // Gui Object
    Circle circleObject;

    public Philosopher(int philosopherNumber, Chopstick leftChopstick, Chopstick rightChopstick, Circle circleObject) {
        this.philosopherNumber = philosopherNumber;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.circleObject = circleObject;
    }


    @Override
    public void run() {
        while (isRunning) {
            try {
                while (isHungry) {

                    // Starting our timer for time spent hungry
                    long start1 = System.currentTimeMillis();

                    if (leftChopstick.isAvailable()) {
                        leftChopstick.pickUp(this, "left");

                        if(rightChopstick.isAvailable()){
                            rightChopstick.pickUp(this, "right");

                            // Tracking the end time for time spent hungry
                            long end1 = System.currentTimeMillis();

                            // Calculating and adding the time spent hungry
                            averageHungryTime += (end1 - start1) / 1000;

                            // Eat
                            eating();
                        }
                        leftChopstick.putDown(this, "left");
                        rightChopstick.putDown(this, "right");
                    }
                    Thread.sleep(500);
                    averageEatingTime += 0.5;
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
        isHungry = false;
        this.circleObject.setFill(Paint.valueOf("#8b282a"));
        int i = randomNumb.nextInt(5000)+1000;
        averageEatingTime += i / 1000;
        Thread.sleep(i);
    }

    public void thinking() throws InterruptedException {
        System.out.println(this.toString() + " is thinking");
        isHungry = true;
        this.circleObject.setFill(Paint.valueOf("#28e7b2"));
        int i = randomNumb.nextInt(5000)+1000;
        averageThinkingTime += i/1000;
        Thread.sleep(i);
    }

    public void hungry() throws InterruptedException{
        System.out.println(this.toString() + " is hungry");
        this.circleObject.setFill(Paint.valueOf("#d7b3ff"));
    }

    public void kill(){
        isRunning = false;
    }

    @Override
    public String toString(){
        return "Philosopher number " + philosopherNumber;
    }

    public double getAverageThinkingTime() {
        return averageThinkingTime;
    }

    public double getAverageEatingTime() {
        return averageEatingTime;
    }

    public double getAverageHungryTime() {
        return averageHungryTime;
    }
}
