import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.*;

public class TemperatureChartApp extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    @Override
    public void start(Stage primaryStage) {
        // Create a line chart with number axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Air Temperature in Helsinki");

        // Retrieve data from the MySQL database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery("SELECT date, temperature FROM temperature_table");

            // Create a series for the line chart
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Temperature");

            // Process the data and add it to the series
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                double temperature = resultSet.getDouble("temperature");
                series.getData().add(new XYChart.Data<>(date.getTime(), temperature));
            }

            // Add the series to the line chart
            lineChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a JavaFX scene with the line chart
        Scene scene = new Scene(lineChart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
