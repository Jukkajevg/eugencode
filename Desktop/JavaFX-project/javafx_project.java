/*
 JavaFX Program gets data from a MySQL database and visualizes data in various ways
*/

package application;
	
import java.sql.*;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;



public class Main extends Application {
	    
	    private Line valueMarker = new Line();
	    
	    private double yShift;
	    
	
	    @Override public void start(Stage stage) throws SQLException, ClassNotFoundException {
        stage.setTitle("JavaFX Project");
        
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day");
        yAxis.setLabel("Temperature");
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        
         
        
        lineChart.setTitle("Temperatures of the first three months of 2018 at Helsinki-Vantaa Airport");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Evgeni Heiskanen");
        
        
        Scene scene  = new Scene(lineChart,1000,800);
        lineChart.getData().add(series);
        
        stage.setScene(scene);
        stage.show();
        Connection conn = null;
        
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ilmalaitos?serverTimezone=Europe/Helsinki&useSSL=false",
            "root", "noos");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT paiva, temperature FROM climate WHERE paiva BETWEEN '2018-01-15' AND '2018-02-15'");
            
            while(rs.next())
            {
                series.getData().add(new XYChart.Data(rs.getString("paiva"), rs.getDouble("temperature")));    
            }
        }
           
        catch (SQLException e) {
        	e.printStackTrace();
        }
        
        finally {
            if (conn != null) {
                conn.close();
            } else {
            	
            } 
        }
	 }
	    
    public static void main(String[] args) {
        launch(args);
    }
}