import java.sql.*;

public class MySQLExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/your_database_name";

    // Database credentials
    static final String USER = "your_username";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a table
            System.out.println("Creating table...");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE Employees " +
                    "(id INT(11) NOT NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(45) NOT NULL, " +
                    " last_name VARCHAR(45) NOT NULL, " +
                    " age INT(11) NOT NULL, " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully!");

            // Insert data into the table
            System.out.println("Inserting records...");
            sql = "INSERT INTO Employees (first_name, last_name, age) " +
                    "VALUES ('John', 'Doe', 30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Employees (first_name, last_name, age) " +
                    "VALUES ('Jane', 'Smith', 25)";
            stmt.executeUpdate(sql);
            System.out.println("Records inserted successfully!");

            // Query the database
            System.out.println("Fetching data...");
            sql = "SELECT id, first_name, last_name, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Age: " + age);
                System.out.println();
            }
            rs.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
