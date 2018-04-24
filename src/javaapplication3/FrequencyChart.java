package javaapplication3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FrequencyChart
{
    private Stage myPlatform;
    private char startingChar;
    private int[] data;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series<String, Number> bars;
    private BarChart<String, Number> chart;

    public FrequencyChart(char sc, int[] d)
    {
        startingChar=sc;
        data=d;
        bars = new XYChart.Series<>();

        myPlatform = new Stage();

    }

    public void setup(){

        xAxis  = new CategoryAxis();
        //xAxis.setLabel("alphabet");

        yAxis = new NumberAxis();
        //yAxis.setLabel("counts");

        //bars.setName("Letters");

        for(char i = 'a'; i<='z'; i++)
        {
            bars.getData().add(new XYChart.Data<String, Number>(Character.toString(i), data[i]));
        }

        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Frequency Analysis");
        chart.getData().add(bars);



        Scene scene = new Scene(chart, 500,500);

        myPlatform.setScene(scene);
       // myPlatform.show();

    }

    public Stage getGraph(){return myPlatform;}
    public void updateData(int[] d){
        data =d;
        bars.getData().clear();
        for(int i = 0; i < data.length; i++)
        {
            // ((char)(i + 'a')) + "" --> add 'a' to get an int representing a char, casting to a char. Then, add "" to get a string
            bars.getData().add(new XYChart.Data<String, Number>(((char)(i + 'a')) + "", data[i]));
        }
    }

}
