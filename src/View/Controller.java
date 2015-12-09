package View;

/**
 * Created by johanrovala on 04/12/15.
 */
import Model.Chopstick;
import Model.Philosopher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;

import java.io.*;


public class Controller {

    int amountOfPhilosophers = 5;
    StringBuilder stringBuilder = new StringBuilder();

    // Java Objects
    Philosopher[] philosophers = new Philosopher[amountOfPhilosophers];
    Chopstick[] chopsticks = new Chopstick[amountOfPhilosophers];
    Thread[] threads = new Thread[amountOfPhilosophers];

   // GUI objects
    Circle[] philosopherCircleList = new Circle[5];
    Rectangle[] chopstickRectangleList = new Rectangle[5];


    @FXML
    private Circle philosopherCircle1;
    @FXML
    private Circle philosopherCircle2;
    @FXML
    private Circle philosopherCircle3;
    @FXML
    private Circle philosopherCircle4;
    @FXML
    private Circle philosopherCircle5;

    @FXML
    private Rectangle chopStick1;
    @FXML
    private Rectangle chopStick2;
    @FXML
    private Rectangle chopStick3;
    @FXML
    private Rectangle chopStick4;
    @FXML
    private Rectangle chopStick5;


    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private GridPane rootLayout;

    File file = new File("myStatistics.txt");

    @FXML
    private void startTheThreads() throws FileNotFoundException, UnsupportedEncodingException {
        PrintStream printWriter = new PrintStream(new FileOutputStream(file));
        System.setOut(printWriter);

        // Instanciate our lists of chopsticks and philosophers
        setUpCircles();
        setUpRectangles();

        for (int i = 0; i < amountOfPhilosophers; i++){
            chopsticks[i] = new Chopstick(chopstickRectangleList[i]);
        }

        // Instanciate our list of philosophers

        for (int i = 0; i < amountOfPhilosophers; i++){
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % amountOfPhilosophers], philosopherCircleList[i]);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }
    }

    @FXML
    private void stopTheThreads() throws FileNotFoundException, UnsupportedEncodingException {
        for(int i = 0; i< amountOfPhilosophers; i++){
            philosophers[i].kill();
           // philosophers[i].finishPrinting();
            stringBuilder.append(philosophers[i].toString() + " | Average Thinking Time: " + philosophers[i].getAverageThinkingTime() + " | Average Eating Time " + philosophers[i].getAverageEatingTime() + " | Average Hungry Time " + philosophers[i].getAverageHungryTime()+ "\n");
        }
        PrintWriter printWriter = new PrintWriter("testAverage.txt" , "UTF-8");
        printWriter.println(stringBuilder.toString());
        printWriter.close();
    }

    private void setUpCircles() {
        philosopherCircleList[0] = philosopherCircle1;
        philosopherCircleList[1] = philosopherCircle2;
        philosopherCircleList[2] = philosopherCircle3;
        philosopherCircleList[3] = philosopherCircle4;
        philosopherCircleList[4] = philosopherCircle5;

    }

    private void setUpRectangles() {
        chopstickRectangleList[0] = chopStick1;
        chopstickRectangleList[1] = chopStick2;
        chopstickRectangleList[2] = chopStick3;
        chopstickRectangleList[3] = chopStick4;
        chopstickRectangleList[4] = chopStick5;

    }
}
