package javaapplication3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        vbMainContain = new VBox();
        hbMethodChoice = new HBox();

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


        Scene root = new Scene(vbMainContain, 300, 500);
        root.getStylesheets().add("resources/Main.css");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(root);
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
                if(!validShift(shiftField.getText()))
                {

                }
                else
                    output[1].setText(ShiftCipher.encode(input.getText(), shiftField.getText().charAt(0)));

            }
            if(ciphers[2].isSelected()) {
                //System.out.println("Repeat listener " + input.getText());
                    /*gets the String from the repeat textfield and uses that as the keyword
                    input validation will be added later*/
                if(!validRepeat(repeatKeyField.getText()))
                {

                }
                else
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
                if(!validShift(shiftField.getText()))
                {

                }
                else
                    output[1].setText(ShiftCipher.decode(input.getText(), shiftField.getText().charAt(0)));

            }
            if(ciphers[2].isSelected() && !repeatKeyField.getText().isEmpty()) {
                    /*gets the String from the repeat textfield and uses that as the keyword
                    input validation will be added later*/
                if(!validRepeat(repeatKeyField.getText()))
                {

                }
                else
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
                String[] freqStrings = RepeatingKeywordCipher.breakCode(input.getText());
                int length = freqStrings.length;
                int[] freq = new int[26];
                for (int i = 0; i < 26; i++) {
                    freq[i] = 0;
                }

                /*for (int i = 0; i < length; i++) {
                    String temp = freqStrings[i];
                    for(int j = 0; j < temp.length(); j++)
                        if (temp.charAt(j) <= 'z' && temp.charAt(j) >= 'a')
                            freq[temp.charAt(j) - 'a'] =  freq[temp.charAt(j) - 'a'] + 1;
                    FrequencyChart fc = new FrequencyChart('a', freq);
                    fc.setup();
                    fc.getGraph().show();
                }*/



                //output[2].setText(RepeatingKeywordCipher.breakCode(input.getText()));
            } else{
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

    public void testFreqChart()
    {
        int[] counts = new int[26];
        for(int i=0;i<counts.length;i++)
            counts[i]=2*i;

        Controller control = new Controller();


        Stage secondaryStage = new Stage();
        FrequencyChart fc = new FrequencyChart(counts, control, secondaryStage);
        Scene root= new Scene(fc.getGraphNode(), 500,300);
        secondaryStage.setTitle("Frequency counts");
        secondaryStage.setScene(root);
        secondaryStage.showAndWait();

        Alert noSelection = new Alert(Alert.AlertType.ERROR);
        noSelection.setTitle("Error");
        noSelection.setHeaderText("No selection was made");
        noSelection.setContentText("Character submitted: " + control.getMsgBetween());

        noSelection.showAndWait();

/*
        for(int i=0;i<counts.length;i++)
            counts[i]=2*counts[i];

        FrequencyChart fc2 = new FrequencyChart(counts);

        Stage ternaryStage = new Stage();
        Scene root2= new Scene(fc2.getGraphNode(), 500,300);
        ternaryStage.setTitle("Frequency counts");
        ternaryStage.setScene(root2);
        ternaryStage.show();*/

    }

}
