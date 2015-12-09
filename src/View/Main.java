package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private GridPane rootLayout;
    @FXML
    private Button startButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("sample.fxml"));
        rootLayout = fxmlLoader.load(Main.class.getResource("sample.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    public void Controller(){

    }


    public static void main(String[] args) {
        launch(args);
    }



}
