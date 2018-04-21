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
    private Map data;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series<String, Number> bars;
    private BarChart<String, Number> chart;

    public FrequencyChart(char sc, Map d)
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
            bars.getData().add(new XYChart.Data<String, Number>(Character.toString(i), Integer.parseInt(data.get(Character.toString(i)).toString())));
        }

        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Frequency Analysis");
        chart.getData().add(bars);



        Scene scene = new Scene(chart, 500,500);

        myPlatform.setScene(scene);
       // myPlatform.show();

    }

    public Stage getGraph(){return myPlatform;}
    public void updateData(Map d){
        data =d;
        bars.getData().clear();
        for(Object s : data.keySet())
        {
            bars.getData().add(new XYChart.Data<String, Number>(s.toString(), Integer.parseInt(data.get(s).toString())));
        }
    }

}
