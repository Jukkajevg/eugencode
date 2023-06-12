import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WaterTemperatureVisualization extends Application {

    private static final String API_URL = "https://api.example.com/temperature-data"; // with the actual API URL

    private List<Double> fetchTemperatureData() {
        
        List<Double> temperatures = new ArrayList<>();
        temperatures.add(2.5);
        temperatures.add(3.1);
        temperatures.add(3.8);
        // ... add more temperature values
        return temperatures;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Water Temperature Visualization");

        // Create the x and y axis for the line chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day");
        yAxis.setLabel("Temperature (°C)");

        // Create the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Water Temperature in Oulu City");

        // Fetch the temperature data
        List<Double> temperatures = fetchTemperatureData();

        // Create the data series for the line chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Temperature");
        
        // Add the temperature data to the series
        int day = 1;
        for (Double temperature : temperatures) {
            series.getData().add(new XYChart.Data<>(day, temperature));
            day++;
        }

        // Add the series to the line chart
        lineChart.getData().add(series);

        // Create a VBox to hold the line chart
        VBox vbox = new VBox(lineChart);
        vbox.setPadding(new Insets(10));

        // Create the scene and set it on the primary stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
