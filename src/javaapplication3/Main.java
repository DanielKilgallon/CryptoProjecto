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
    private HBox shiftContainer;
    private HBox repeatContainer;

    private ToggleButton tbtnEncode;
    private ToggleButton tbtnDecode;
    private ToggleButton tbtnBreak;
    private ToggleGroup tgMethodChoice;

    private TextArea input;
    private TextArea[] output;

    private TextField shiftField;
    private TextField repeatKeyField;

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
        tgMethodChoice.getToggles().get(0).setSelected(true); //preselection -- starts with this button toggled
        vbMainContain = new VBox();
        hbMethodChoice = new HBox();

        ciphers = new CheckBox[NUM_CIPHERS];
        ciphers[0] = new CheckBox("Atbash");
        ciphers[1] = new CheckBox("Shift");
        ciphers[2] = new CheckBox("Repeating");

        input = new TextArea();
        input.setPromptText("Your message here...");

        output = new TextArea[NUM_CIPHERS];
        for (int i = 0; i < NUM_CIPHERS; i++) {
            output[i] = new TextArea();
            output[i].setPromptText("Output will go here...");
            output[i].setWrapText(true);
            output[i].setEditable(false);
            output[i].setDisable(true);
        }

        shiftField = new TextField();
        repeatKeyField = new TextField();

        shiftContainer = new HBox();
        repeatContainer = new HBox();

        shiftContainer.getChildren().add(new Label("Shift: "));
        shiftContainer.getChildren().add(shiftField);
        shiftContainer.setDisable(true);
        repeatContainer.getChildren().add(new Label("Keyword: "));
        repeatContainer.getChildren().add(repeatKeyField);
        repeatContainer.setDisable(true);



        //setup of the button actions
        ciphers[0].setOnAction(event -> {
            output[0].setDisable(!output[0].isDisabled());//toggles whether or not the output boxes will be active
        });
        ciphers[1].setOnAction(event -> {
            output[1].setDisable(!output[1].isDisabled());//toggles whether or not the output boxes will be active
            shiftContainer.setDisable(!shiftContainer.isDisabled());
        });
        ciphers[2].setOnAction(event -> {
            output[2].setDisable(!output[2].isDisabled());//toggles whether or not the output boxes will be active
            repeatContainer.setDisable(!repeatContainer.isDisabled());
        });

        hbMethodChoice.getChildren().addAll(tbtnEncode, tbtnDecode, tbtnBreak);
        vbMainContain.getChildren().add(hbMethodChoice);
        for (int i = 0; i < ciphers.length; i++) {
            vbMainContain.getChildren().add(ciphers[i]);
            vbMainContain.getChildren().add(output[i]);
            if(i==1)
                vbMainContain.getChildren().add(shiftContainer);
            if(i==2)
                vbMainContain.getChildren().add(repeatContainer);
        }

        //vbMainContain.getChildren().addAll(ciphers);
        vbMainContain.getChildren().add(input);

        process = new Button("Process");
        //vbMainContain.getChildren().add(output);
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
