import java.sql.*;

public class WeatherForecastProgram {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/datajalometeo";
    private static final String DB_USERNAME = "evgeenii";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing the database connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Creating a SQL statement
            statement = connection.createStatement();

            // SQL query to retrieve weather forecast data
            String sqlQuery = "SELECT * FROM weather_forecast";

            // Executing the query
            resultSet = statement.executeQuery(sqlQuery);

            // Processing the result set
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String location = resultSet.getString("location");
                double temperature = resultSet.getDouble("temperature");
                String description = resultSet.getString("description");

                System.out.println("Date: " + date);
                System.out.println("Location: " + location);
                System.out.println("Temperature: " + temperature);
                System.out.println("Description: " + description);
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
