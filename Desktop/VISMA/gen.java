import java.sql.*;

public class GeneCountCalculator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gen";
        String username = "eugen";
        String password = "password";

        try {
            // Establishing a connection to the MySQL database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Executing a query to retrieve the number of genes
            String query = "SELECT COUNT(*) AS gene_count FROM genes";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Retrieving the result
            if (resultSet.next()) {
                int geneCount = resultSet.getInt("gene_count");
                System.out.println("Number of genes: " + geneCount);
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
