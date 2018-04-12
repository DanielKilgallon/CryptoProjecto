package javaapplication3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private VBox vbMainContain;
    private HBox hbMethodChoice;
    private ToggleButton tbtnEncode;
    private ToggleButton tbtnDecode;
    private ToggleButton tbtnBreak;
    private ToggleGroup tgMethodChoice;

    @Override
    public void start(Stage primaryStage) throws Exception{

        tgMethodChoice = new ToggleGroup();
        tbtnEncode = new ToggleButton("Encode");
        tbtnDecode = new ToggleButton("Decode");
        tbtnBreak = new ToggleButton("Break");
        vbMainContain = new VBox();
        hbMethodChoice = new HBox();

        hbMethodChoice.getChildren().addAll(tbtnEncode, tbtnDecode, tbtnBreak);
        vbMainContain.getChildren().add(hbMethodChoice);


        Scene root = new Scene(vbMainContain, 300, 500);
        root.getStylesheets().add("resources/Main.css");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
