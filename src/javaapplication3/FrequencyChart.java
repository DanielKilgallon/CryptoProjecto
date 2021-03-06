package javaapplication3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FrequencyChart
{
    private VBox myPlatform;
    private VBox bottomBox;
    private HBox buttonHolder;
    private Button next;
    private Button previous;
    private Button submit;
    private Label lblMsg;
    private Label lblShift;
    private int[] letterCount;
    private char currentShift;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series<String, Number> bars;
    private BarChart<String, Number> chart;
    private Controller control;
    private Stage stg;
    private String currentKey;
    private double keyLength;

    public FrequencyChart(int[] lc, Controller c, Stage s, String ck, double kl)
    {
        letterCount=lc;
        control = c;
        stg = s;
        bars = new XYChart.Series<>();
        myPlatform = new VBox();
        currentKey=ck;
        keyLength=kl;
        setup();
    }

    private void setup(){
        bottomBox = new VBox();
        buttonHolder = new HBox();
        next = new Button("right");
        next.setOnAction(e -> shift((char)(currentShift-1)));
        next.setMinWidth(40);
        previous = new Button("left");
        previous.setOnAction(e -> shift((char)(currentShift+1)));
        previous.setMinWidth(40);
        submit = new Button("Submit");
        submit.setOnAction(e-> {
            control.setMsgBetween(lblShift.getText());
            stg.close();

            /*
                stg refers to the Stage that will contain the FrequencyChart object.
                The only reason it exists in this class is to close it on a button press.
             */
        });
        lblMsg = new Label("Currently shifted with: ");
        lblShift = new Label();

        xAxis  = new CategoryAxis();
        yAxis = new NumberAxis();

        HBox shiftBox = new HBox();
        shiftBox.getChildren().add(lblMsg);
        shiftBox.getChildren().add(lblShift);
        shiftBox.setAlignment(Pos.CENTER);
        shiftBox.setPadding(new Insets(5));
        VBox keywordBox = new VBox(8);
        keywordBox.getChildren().add(new Label("Estimated keyword length: " + (int)keyLength));
        keywordBox.getChildren().add(new Label("Keyword being built: " + currentKey));
        keywordBox.setPadding(new Insets(5,5,8,5));
        keywordBox.setAlignment(Pos.CENTER);
        buttonHolder.getChildren().add(previous);
        buttonHolder.getChildren().add(next);
        buttonHolder.setAlignment(Pos.CENTER);
        bottomBox.getChildren().add(buttonHolder);
        bottomBox.getChildren().add(shiftBox);
        bottomBox.getChildren().add(keywordBox);
        bottomBox.getChildren().add(submit);
        bottomBox.setAlignment(Pos.CENTER);


        currentShift='a';
        lblShift.setText(currentShift+"");
        for(char i = 'a'; i<='z'; i++)
        {
            XYChart.Data d = new XYChart.Data<String, Number>(i+"", letterCount[i-'a']);
            addLabel(d, i);
            bars.getData().add(d);
        }

        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Frequency Analysis");
        chart.setLegendVisible(false);
        chart.getData().add(bars);
        myPlatform.getChildren().add(chart);
        myPlatform.getChildren().add(bottomBox);

    }

    private void shift(char c)//uses lowercase char math
    {
        currentShift = c; //assigned to the next char

        //handles any chars outside of the alphabet
        if(currentShift < 'a')
            currentShift = 'z';
        if(currentShift > 'z')
            currentShift = 'a';

        lblShift.setText(currentShift+"");
       /* Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(currentShift + "is the current shift");
        a.showAndWait();*/

        int i=0; //loop control
        while(i<letterCount.length)
        {
            if(c > 'z') //reached the end of the array
                c-=letterCount.length;
            if(c < 'a') //reached the beginning of the array
                c+=letterCount.length;

            addLabel(bars.getData().get(i), c); //adding the appropriate character as a label

            c+=1;
            i++;
        }

        /*
        1. Creates a new BarChart
        2. Adds the XYChart.Series to the new BarChart
        3. Removes the legend
        4. Removes the old BarChart from the VBox, myPlatform
        5. Reassigns the new BarChart to the old and adds it back to myPlatform
         */
        BarChart bc = new BarChart(new CategoryAxis(), new NumberAxis());
        bc.getData().add(bars);
        bc.setLegendVisible(false);
        myPlatform.getChildren().remove(chart);
        chart=bc;
        myPlatform.getChildren().add(0,chart);
    }

   // copied mostly from: https://coderanch.com/t/675678/java/add-label-individual-bar-bar

    private void addLabel(XYChart.Data xydata, char c)
   {
       String text = c + "";

       StackPane node = new StackPane();
       Label label = new Label(text);
       Group group = new Group(label);
       //positioning
       StackPane.setAlignment(group, Pos.TOP_CENTER);
       group.setTranslateY(-20);
       //adding the group (holding the text) and the bar to the StackPane, node
       node.getChildren().add(group);
       xydata.setNode(node);
   }

    public VBox getGraphNode(){return myPlatform;}
}
