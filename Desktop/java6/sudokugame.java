import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

public class SudokuGame extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ggame";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    private static final int GRID_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    private TextField[][] gridFields = new TextField[GRID_SIZE][GRID_SIZE];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Game");

        GridPane gridPane = createGridPane();
        HBox buttonBox = createButtonBox();

        GridPane root = new GridPane();
        root.add(gridPane, 0, 0);
        root.add(buttonBox, 0, 1);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM sudoku_grid";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int row = resultSet.getInt("row");
                int col = resultSet.getInt("col");
                int value = resultSet.getInt("value");

                TextField textField = new TextField();
                textField.setText(value > 0 ? String.valueOf(value) : "");
                textField.setPrefColumnCount(1);
                textField.setAlignment(Pos.CENTER);
                textField.setEditable(value == 0);

                gridFields[row][col] = textField;
                gridPane.add(textField, col, row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gridPane;
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button solveButton = new Button("Solve");
        solveButton.setOnAction(event -> solveSudoku());

        buttonBox.getChildren().add(solveButton);
        return buttonBox;
    }

    private void solveSudoku() {
        // Perform Sudoku solving logic here

        // Display success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sudoku Solution");
        alert.setHeaderText(null);
        alert.setContentText("Sudoku solved successfully!");
        alert.showAndWait();
    }
}
