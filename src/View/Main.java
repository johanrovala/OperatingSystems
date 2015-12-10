package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    // Fetch the layout Container in Scenebuilder

    @FXML
    private GridPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("sample.fxml"));
        rootLayout = fxmlLoader.load(Main.class.getResource("sample.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }

    // Boilerplate stuff, main method

    public static void main(String[] args) {
        launch(args);
    }



}
