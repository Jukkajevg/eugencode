import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.util.Random;

public class HelsinkiTemperatureDistribution extends Application {
    private static final int NUM_OF_DATA_POINTS = 1000;
    private static final double MEAN = 5.0;
    private static final double STANDARD_DEVIATION = 10.0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Helsinki Temperature Distribution");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final javafx.scene.chart.ScatterChart<Number, Number> scatterChart = new javafx.scene.chart.ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Temperature Distribution in Helsinki");

        xAxis.setLabel("Temperature (°C)");
        yAxis.setLabel("Frequency");

        Series<Number, Number> series = new Series<>();
        series.setName("Temperature Distribution");

        Random random = new Random();
        ObservableList<Data<Number, Number>> data = FXCollections.observableArrayList();

        for (int i = 0; i < NUM_OF_DATA_POINTS; i++) {
            double temperature = random.nextGaussian() * STANDARD_DEVIATION + MEAN;
            data.add(new Data<>(temperature, i));
        }

        series.setData(data);
        scatterChart.getData().add(series);

        for (Data<Number, Number> item : series.getData()) {
            Tooltip tooltip = new Tooltip(String.format("(%.2f, %d)", item.getXValue().doubleValue(), item.getYValue().intValue()));
            Tooltip.install(item.getNode(), tooltip);
        }

        Group root = new Group(scatterChart);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
