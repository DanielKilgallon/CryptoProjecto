package javaapplication3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Set;

public class FrequencyChart extends Application
{

    private char startingChar;
    private Set data;
    private XYChart.Series<String, Number> bars;
    private BarChart<String, Number> chart;

    public FrequencyChart(char sc, Set d)
    {
        startingChar=sc;
        data=d;

        try {
            this.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        chart = new BarChart<String, Number>(new CategoryAxis(), new NumberAxis());

        bars = new XYChart.Series<String, Number>();
        bars.getData().add(new XYChart.Data<>("a", 12));
        bars.getData().add(new XYChart.Data<>("d", 76));
        bars.getData().add(new XYChart.Data<>("k", 132));
        bars.getData().add(new XYChart.Data<>("m", 46));
        bars.getData().add(new XYChart.Data<>("q", 2));

        Scene scene = new Scene(chart, 500,500);
        chart.getData().add(bars);

        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
