import Model.Chopstick;
import Model.Philosopher;

/**
 * Created by johanrovala on 27/11/15.
 */
public class testMain {
    /*
     * This class is just meant to test the functionality of Chopstick and Philosopher
     */

    public static void main(String[] args) throws InterruptedException {
        int amountOfPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[amountOfPhilosophers];
        Chopstick[] chopsticks = new Chopstick[amountOfPhilosophers];

        // Instanciate our list of chopsticks
/*
        for (int i = 0; i < amountOfPhilosophers; i++){
            chopsticks[i] = new Chopstick();
        }

        // Instanciate our list of philosophers
/*
        for (int i = 0; i < amountOfPhilosophers; i++){
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % amountOfPhilosophers]);
            new Thread(philosophers[i]).start();
            Thread.sleep(2000);
        }

*/

    }


}
