import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.*;

public class DatabaseVisualization extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/temperature_helsinki";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the scatter chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Temperature in Helsinki");
        xAxis.setLabel("Day");
        yAxis.setLabel("Temperature");

        // Create a series for the scatter chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Temperature Data");

        try {
            // Establishing the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Creating a SQL statement
            Statement statement = connection.createStatement();

            // SQL query to retrieve temperature data
            String sqlQuery = "SELECT day, temperature FROM temperature_data";

            // Executing the query
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the result set
            while (resultSet.next()) {
                int day = resultSet.getInt("day");
                double temperature = resultSet.getDouble("temperature");

                // Add data to the series
                series.getData().add(new XYChart.Data<>(day, temperature));
            }

            // Add the series to the scatter chart
            scatterChart.getData().add(series);

            // Create a scene and set it on the stage
            Scene scene = new Scene(scatterChart, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Closing the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
