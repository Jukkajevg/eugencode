import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLDataVisualization extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the chart axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        // Set chart title and axis labels
        barChart.setTitle("Data Visualization");
        xAxis.setLabel("Category");
        yAxis.setLabel("Value");

        // Fetch data from the database
        ObservableList<XYChart.Data<String, Number>> data = fetchDataFromDatabase();

        // Create a series and add data to it
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);

        // Add the series to the chart
        barChart.getData().add(series);

        // Create a scene and display the chart
        Scene scene = new Scene(barChart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<XYChart.Data<String, Number>> fetchDataFromDatabase() {
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();

        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute the query
            String query = "SELECT category, value FROM statistics";
            ResultSet rs = stmt.executeQuery(query);

            // Fetch data from the result set and add it to the list
            while (rs.next()) {
                String category = rs.getString("category");
                int value = rs.getInt("value");
                data.add(new XYChart.Data<>(category, value));
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
