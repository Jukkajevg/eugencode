import java.sql.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatisticsExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "your-username";
        String password = "your-password";

        try {
            // Establishing a connection to the MySQL database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Querying the database to fetch the data for statistical analysis
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mytable");

            // Creating an array to store the data for statistical calculations
            double[] data = new double[resultSet.getFetchSize()];
            int i = 0;

            // Populating the data array with values from the database
            while (resultSet.next()) {
                data[i] = resultSet.getDouble("value");
                i++;
            }

            // Using Apache Commons Math to perform statistical calculations
            DescriptiveStatistics stats = new DescriptiveStatistics(data);

            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();
            double median = stats.getPercentile(50);

            System.out.println("Mean: " + mean);
            System.out.println("Standard Deviation: " + stdDev);
            System.out.println("Median: " + median);

            // Closing the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
