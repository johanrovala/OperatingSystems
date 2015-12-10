package Model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

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

    private int numbTimesThinking = 0;
    private int numbTimesEating = 0;
    private int numbTimesHungry = 0;
    private int res = 0;

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
                            this.res = (int) (end1 - start1);
                            this.averageHungryTime += this.res;

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
                    break;
                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
     * Method for eating state
     */

    public void eating() throws InterruptedException {
        System.out.println(this.toString() + " is eating");
        isHungry = false;
        this.circleObject.setFill(Paint.valueOf("#8b282a"));
        numbTimesEating++;
        int i = randomNumb.nextInt(5000)+1000;
        averageEatingTime += i / 1000;
        Thread.sleep(i);
    }

    /*
     * Method for thinking state
     */

    public void thinking() throws InterruptedException {
        System.out.println(this.toString() + " is thinking");
        isHungry = true;
        this.circleObject.setFill(Paint.valueOf("#28e7b2"));
        numbTimesThinking++;
        int i = randomNumb.nextInt(5000)+1000;
        averageThinkingTime += i/1000;
        Thread.sleep(i);
    }

    /*
     * Method for hungry state
     */

    public void hungry() throws InterruptedException{
        System.out.println(this.toString() + " is hungry");
        this.circleObject.setFill(Paint.valueOf("#d7b3ff"));
        numbTimesHungry++;
    }

    /*
     * Method for 'stopping' the thread.
     */
    public void kill(){
        isRunning = false;
    }

    @Override
    public String toString(){
        return "Philosopher number " + philosopherNumber;
    }


    /*
     * Getters for the average time variables
     */

    public double getAverageThinkingTime() {
        return this.averageThinkingTime / numbTimesThinking;
    }

    public double getAverageEatingTime() {
        return this.averageEatingTime / numbTimesEating;
    }

    public double getAverageHungryTime() {
        return this.averageHungryTime / numbTimesHungry;
    }
}
