package Model;

import java.util.Random;

/**
 * Created by johanrovala on 27/11/15.
 */
public class Philosopher implements Runnable{

    private Chopstick leftChopstick;
    private Chopstick rightChopstick;

    private boolean hasEaten = false;

    private Random randomNumb = new Random();
    private int philosopherNumber;

    public Philosopher(int philosopherNumber, Chopstick leftChopstick, Chopstick rightChopstick){
        this.philosopherNumber = philosopherNumber;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }


    @Override
    public void run() {
        while(!hasEaten){
            try {
                thinking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(leftChopstick.isAvailable()){
                if(rightChopstick.isAvailable()){
                    try {
                        eating();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rightChopstick.putDown(this, "right");
                }
                leftChopstick.putDown(this, "left");
            }
        }
    }

    public void eating() throws InterruptedException {
        System.out.println(this.toString() + " is eating");
        int i = randomNumb.nextInt(30)+1;
        Thread.sleep(i);
        hasEaten = true;
    }

    public void thinking() throws InterruptedException {
        System.out.println(this.toString() + " is eating");
        int i = randomNumb.nextInt(30)+1;
        Thread.sleep(i);
    }

    @Override
    public String toString(){
        return "Philosopher number" + philosopherNumber;
    }
}
