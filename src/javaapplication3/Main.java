package javaapplication3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

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
        vbMainContain = new VBox(7);
        hbMethodChoice = new HBox(7);
        hbMethodChoice.setAlignment(Pos.CENTER);

        ciphers = new CheckBox[NUM_CIPHERS];
        ciphers[0] = new CheckBox("Atbash");
        ciphers[1] = new CheckBox("Shift");
        ciphers[2] = new CheckBox("Repeating");

        input = new TextArea();
        input.setPromptText("Your message here...");
        input.setWrapText(true);

        output = new TextArea[NUM_CIPHERS];
        for (int i = 0; i < NUM_CIPHERS; i++) {
            output[i] = new TextArea();
            output[i].setPromptText("Output will appear here...");
            output[i].setWrapText(true);
            output[i].setEditable(false);
            output[i].setDisable(true);
        }

        //input validation for shift and keyword cipher textfields
        shiftField = new TextField();
        shiftField.textProperty().addListener(e->{
            if(!validShift(shiftField.getText()) && ciphers[1].isSelected())
                shiftField.setId("error");
            else
                shiftField.setId("");
        });
        repeatKeyField = new TextField();
        repeatKeyField.textProperty().addListener(e->{
            if(!validRepeat(repeatKeyField.getText()) && ciphers[2].isSelected())
                repeatKeyField.setId("error");
            else
                repeatKeyField.setId("");
        });

        shiftContainer = new HBox();
        repeatContainer = new HBox();

        shiftContainer.getChildren().add(new Label("Shift: "));
        shiftContainer.getChildren().add(shiftField);
        shiftContainer.setDisable(true);
        shiftContainer.setId("shiftcontainers");
        repeatContainer.getChildren().add(new Label("Keyword: "));
        repeatContainer.getChildren().add(repeatKeyField);
        repeatContainer.setDisable(true);
        repeatContainer.setId("shiftcontainers");



        //setup of the button actions
        ciphers[0].setOnAction(event -> {
            output[0].setDisable(!output[0].isDisabled());//toggles whether or not the output boxes will be active
        });
        ciphers[1].setOnAction(event -> {
            output[1].setDisable(!output[1].isDisabled());//toggles whether or not the output boxes will be active
            if(tbtnBreak.isSelected()){}//for the "break" option, keep the shift box disabled
            else
                shiftContainer.setDisable(!shiftContainer.isDisabled()); //otherwise enable it
        });
        ciphers[2].setOnAction(event -> {
            output[2].setDisable(!output[2].isDisabled());//toggles whether or not the output boxes will be active
            if(tbtnBreak.isSelected()){}//for the "break" option, keep the repeating keyword box disabled
            else
                repeatContainer.setDisable(!repeatContainer.isDisabled()); //otherwise enable it
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


        /*
        Disables all fields when the "mode" is changed (switching from decode to encode)
        additionally, clears the output boxes and key text fields
         */
        tgMethodChoice.selectedToggleProperty().addListener(event -> {
            for(CheckBox cb : ciphers)
                cb.setSelected(false);
            for(TextArea ta : output) {
                ta.setDisable(true);
                ta.clear();
            }
            shiftField.clear();
            shiftContainer.setDisable(true);
            repeatKeyField.clear();
            repeatContainer.setDisable(true);
        });

        //the cipher methods should be called here for active updates to the output areas
//        input.textProperty().addListener( e->{
//           setupInputToOutput();
//        });

        process.setOnAction(e -> setupInputToOutput());


        Scene root = new Scene(vbMainContain);
        root.getStylesheets().add("resources/Main.css");
        primaryStage.setTitle("Killow Coder");
        primaryStage.setScene(root);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(500);
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);

        primaryStage.show();

//        testFreqChart();
    }

    private boolean validShift(String s)
    {
        if(s.isEmpty())
            return false;
        if(s.length()>1)
            return false;
        if(!Character.isLetter(s.charAt(0)))
            return false;
        return true;
    }

    private boolean validRepeat(String s)
    {
        if(s.isEmpty())
            return false;
        if(s.length()==0)
            return false;

        for (int i = 0; i < s.length(); i++) {
            if(!Character.isLetter(s.charAt(i)))
                return false;
        }
        return true;
    }

    private void setupInputToOutput()
    {
        if(tbtnEncode.isSelected())
        {
            if(ciphers[0].isSelected())
                output[0].setText(AtbashCipher.encode(input.getText()));
            if(ciphers[1].isSelected()) {
                //System.out.println("Shift listener " + input.getText());
                    /*gets the first character in the shift textfield and uses that as the ?-shift
                    input validation will be added later*/
                if(validShift(shiftField.getText()))
                    output[1].setText(ShiftCipher.encode(input.getText(), shiftField.getText().charAt(0)));
            }
            if(ciphers[2].isSelected()) {
                //System.out.println("Repeat listener " + input.getText());
                    /*gets the String from the repeat textfield and uses that as the keyword
                    input validation will be added later*/
                if(validRepeat(repeatKeyField.getText()))
                    output[2].setText(RepeatingKeywordCipher.encode(input.getText(), repeatKeyField.getText()));
            }
        }
        else if(tbtnDecode.isSelected())
        {
            if(ciphers[0].isSelected())
                output[0].setText(AtbashCipher.decode(input.getText()));
            if(ciphers[1].isSelected()) {
                    /*gets the first character in the shift textfield and uses that as the ?-shift
                    input validation will be added later*/
                if(validShift(shiftField.getText()))
                    output[1].setText(ShiftCipher.decode(input.getText(), shiftField.getText().charAt(0)));

            }
            if(ciphers[2].isSelected() && !repeatKeyField.getText().isEmpty()) {
                    /*gets the String from the repeat textfield and uses that as the keyword
                    input validation will be added later*/
                if(validRepeat(repeatKeyField.getText()))
                    output[2].setText(RepeatingKeywordCipher.decode(input.getText(), repeatKeyField.getText()));
            }
        }

        else if(tbtnBreak.isSelected())
        {
            if(ciphers[0].isSelected())
                output[0].setText(AtbashCipher.decode(input.getText()));
            if(ciphers[1].isSelected()) {
                //runs through the alphabet and prints all possibilities
                String[] all = ShiftCipher.breakCode(input.getText());
                output[1].setText(all[0]);
                for(int i=1; i<all.length; i++)
                    output[1].appendText(all[i]);
            }
            /*gets the String from the repeat textfield and uses that as the keyword
            input validation will be added later*/
            if(ciphers[2].isSelected()) {
                String formatted = input.getText().replaceAll("\\s", ""); //removes all spaces
                formatted = formatted.replaceAll("\\W", "");//removes anything that isn't a letter

                String[] freqStrings = RepeatingKeywordCipher.breakCode(formatted); //spaces wreck the char math --- weed them out before calling this function
                int[] freq = new int[26];

                String keyword="";
                Controller control = new Controller(); //used to pass strings between the FrequencyChart and the Main classes

                //only goes through the array enough times for the length of the keyword
                for (int i = 0; i < freqStrings.length; i++) {
                    for (int j = 0; j < 26; j++) {
                        freq[j] = 0;
                    }//resets the counts to 0

                    freqStrings[i] = freqStrings[i].toLowerCase();
                    for (int k = i; k < freqStrings[i].length(); k++) {
                        char ch = freqStrings[i].charAt(k);
                        freq[ch-'a'] += 1;
                    }//counts the letters based on gcd

                    Stage secondStage = new Stage();
                    FrequencyChart fc = new FrequencyChart(freq, control, secondStage, keyword, freqStrings.length);
                    Scene tempScene = new Scene(fc.getGraphNode());
                    secondStage.initModality(Modality.APPLICATION_MODAL);
                    secondStage.setAlwaysOnTop(true);
                    secondStage.setScene(tempScene);
                    secondStage.setTitle("Frequency Analysis");
                    secondStage.setWidth(1080);
                    secondStage.setWidth(720);
                    secondStage.showAndWait();
                    //displays a frequency chart and waits for input

                    keyword += control.getMsgBetween();
                    //builds up the keyword

                }

                if (keyword.length() != 0)
                 output[2].setText(RepeatingKeywordCipher.decode(input.getText(), keyword));
                else
                    output[2].setText("Insufficient Data To Break!");
            }
       }

       else{
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("No selection was made");
            noSelection.setContentText("Choose Encode, Decode, or Break");

            noSelection.showAndWait();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
