import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProtonDistributionVisualization extends Application {

    private static final int SAMPLE_SIZE = 1000; // Define the sample size
    private static final int MEAN_PROTONS = 3; // Define the mean number of protons
    private static final double STANDARD_DEVIATION = 0.5; // Define the standard deviation

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Proton Distribution Visualization");

        // Create the x and y axis for the histogram
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Protons");
        yAxis.setLabel("Frequency");

        // Create the histogram
        BarChart<String, Number> histogram = new BarChart<>(xAxis, yAxis);
        histogram.setTitle("Distribution of Protons in Lithium");
        histogram.setLegendSide(Side.BOTTOM);

        // Generate random data points following a normal distribution
        List<Integer> protonCounts = generateProtonCounts();

        // Create a frequency distribution for the data points
        List<Integer> frequencies = createFrequencyDistribution(protonCounts);

        // Create the data series for the histogram
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Proton Count");

        // Add the data to the series
        for (int i = 0; i < frequencies.size(); i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), frequencies.get(i)));
        }

        // Add the series to the histogram
        histogram.getData().add(series);

        // Create the scene and set it on the primary stage
        Scene scene = new Scene(histogram, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to generate random data points following a normal distribution
    private List<Integer> generateProtonCounts() {
        List<Integer> protonCounts = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            int protonCount = (int) (random.nextGaussian() * STANDARD_DEVIATION + MEAN_PROTONS);
            protonCounts.add(protonCount);
        }

        return protonCounts;
    }

    // Method to create a frequency distribution for the data points
    private List<Integer> createFrequencyDistribution(List<Integer> protonCounts) {
        List<Integer> frequencies = new ArrayList<>();

        for (int i = 0; i <= MEAN_PROTONS + 3; i++) { // Adjust the upper limit as needed
            int frequency = 0;
            for (int count : protonCounts) {
                if (count == i) {
                    frequency++;
                }
            }
            frequencies.add(frequency);
        }

        return frequencies;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
