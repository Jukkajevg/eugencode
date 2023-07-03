import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.*;

public class DatabaseVisualization extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/meteoxce";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the line chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Average Sum of Temperatures by Year");
        xAxis.setLabel("Year");
        yAxis.setLabel("Average Sum of Temperatures");

        // Create a series for the line chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Sum of Temperatures");

        try {
            // Establishing the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Creating a SQL statement
            Statement statement = connection.createStatement();

            // SQL query to retrieve average sum of temperatures by years from 2001 to 2020
            String sqlQuery = "SELECT YEAR(date) AS year, AVG(sum_temperature) AS avg_sum_temperature " +
                    "FROM meteo_data " +
                    "WHERE YEAR(date) BETWEEN 2001 AND 2020 " +
                    "GROUP BY YEAR(date) " +
                    "ORDER BY YEAR(date)";

            // Executing the query
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Processing the result set
            while (resultSet.next()) {
                String year = resultSet.getString("year");
                double averageSumTemperature = resultSet.getDouble("avg_sum_temperature");

                // Add data to the series
                series.getData().add(new XYChart.Data<>(year, averageSumTemperature));
            }

            // Add the series to the line chart
            lineChart.getData().add(series);

            // Create a scene and set it on the stage
            Scene scene = new Scene(lineChart, 800, 600);
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
