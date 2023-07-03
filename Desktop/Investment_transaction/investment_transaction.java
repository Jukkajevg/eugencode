import java.sql.*;

public class InvestmentTransactionRetriever {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/economic_abccd";
    private static final String DB_USERNAME = "your_username";
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

            // SQL query to retrieve investment transactions
            String sqlQuery = "SELECT * FROM investment_transactions";

            // Executing the query
            resultSet = statement.executeQuery(sqlQuery);

            // Variables for calculating expected value
            double totalAmount = 0;
            int count = 0;

            // Processing the result set
            while (resultSet.next()) {
                // Retrieve data for each investment transaction
                double amount = resultSet.getDouble("amount");

                // Calculate the sum of amounts
                totalAmount += amount;
                count++;
            }

            // Calculate the expected value
            double expectedValue = totalAmount / count;

            System.out.println("Total transactions: " + count);
            System.out.println("Total amount: " + totalAmount);
            System.out.println("Expected value: " + expectedValue);
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
