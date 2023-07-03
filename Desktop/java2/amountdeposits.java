import java.sql.*;

public class DepositAverageCalculator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/advertism";
    private static final String DB_USERNAME = "evgeen";
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

            // SQL query to retrieve average amount of deposits by years from 2008 to 2019
            String sqlQuery = "SELECT YEAR(date) AS year, AVG(amount) AS avg_amount " +
                    "FROM deposits " +
                    "WHERE YEAR(date) BETWEEN 2008 AND 2019 " +
                    "GROUP BY YEAR(date) " +
                    "ORDER BY YEAR(date)";

            // Executing the query
            resultSet = statement.executeQuery(sqlQuery);

            // Processing the result set
            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                double averageAmount = resultSet.getDouble("avg_amount");

                System.out.println("Year: " + year);
                System.out.println("Average Amount: " + averageAmount);
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
