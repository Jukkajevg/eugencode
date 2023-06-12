import java.sql.*;

public class InvestorCalculator {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/database_name";
        String username = "eugen";
        String password = "password";

        // SQL query to calculate the number of investors
        String sql = "SELECT COUNT(*) FROM investors WHERE industry = 'Vantaa'";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Extract the result
            if (resultSet.next()) {
                int investorCount = resultSet.getInt(1);
                System.out.println("Number of investors in Vantaa industry: " + investorCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
