package javaapplication3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final int NUM_CIPHERS=3;

    private VBox vbMainContain;
    private HBox hbMethodChoice;

    private ToggleButton tbtnEncode;
    private ToggleButton tbtnDecode;
    private ToggleButton tbtnBreak;
    private ToggleGroup tgMethodChoice;

    private TextArea input;
    private TextArea output;

    private CheckBox[] ciphers;

    private Button process;

    @Override
    public void start(Stage primaryStage) throws Exception{

        tbtnEncode = new ToggleButton("Encode");
        tbtnDecode = new ToggleButton("Decode");
        tbtnBreak = new ToggleButton("Break");
        tgMethodChoice = new ToggleGroup();
        tgMethodChoice.getToggles().add(tbtnEncode);
        tgMethodChoice.getToggles().add(tbtnDecode);
        tgMethodChoice.getToggles().add(tbtnBreak);
        //tgMethodChoice.getToggles().get(0).setSelected(true); //preselection -- starts with this button toggled
        vbMainContain = new VBox();
        hbMethodChoice = new HBox();

        ciphers = new CheckBox[NUM_CIPHERS];
        ciphers[0] = new CheckBox("Atbash");
        ciphers[1] = new CheckBox("Shift");
        ciphers[2] = new CheckBox("Repeating");

        input = new TextArea();
        input.setPromptText("Your message here...");
        output = new TextArea("Output will go here");
        output.setEditable(false);
        output.setFocusTraversable(false);

       process = new Button("Process");


        hbMethodChoice.getChildren().addAll(tbtnEncode, tbtnDecode, tbtnBreak);
        vbMainContain.getChildren().add(hbMethodChoice);
        vbMainContain.getChildren().addAll(ciphers);
        vbMainContain.getChildren().add(input);
        vbMainContain.getChildren().add(output);
        vbMainContain.getChildren().add(process);



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
