package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutController {
    @FXML
    private GridPane workoutGrid;
    @FXML
    private Button newWorkoutButton;

    @FXML
    protected void onNewWorkoutButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workoutItem.fxml"));
        AnchorPane workoutItem = fxmlLoader.load();

        int numRows = workoutGrid.getRowConstraints().size();
        int numCols = workoutGrid.getColumnConstraints().size();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (isCellEmpty(i, j)) {
                    workoutGrid.add(workoutItem, j, i);
                    return;
                }
            }
        }

    }
    private boolean isCellEmpty(int row, int col) {

        for (Node child : workoutGrid.getChildren()) {
            if (!child.isVisible() || !child.isManaged()) {
                continue;
            }
            // Get row and column index, treat null as 0
            Integer rowIndex = GridPane.getRowIndex(child);
            int childRow = (rowIndex == null) ? 0 : rowIndex;

            Integer colIndex = GridPane.getColumnIndex(child);
            int childCol = (colIndex == null) ? 0 : colIndex;

            // Check if the current cell is occupied
            if (childRow == row && childCol == col) {
                return false; // This cell is not empty
            }
        }
        return true; // This cell is empty
    }

    private List<GridPaneState> saveGridPaneState() {
        List<GridPaneState> stateList = new ArrayList<>();
        for (Node child : workoutGrid.getChildren()) {
            int row = GridPane.getRowIndex(child) != null ? GridPane.getRowIndex(child) : 0;
            int col = GridPane.getColumnIndex(child) != null ? GridPane.getColumnIndex(child) : 0;
            String content = ((Label)child).getText(); // Assuming the content is a Label, adapt as needed

            stateList.add(new GridPaneState(row, col, content));
        }
        return stateList;
    }

    void restoreGridPaneState(List<GridPaneState> stateList) {
        // workoutGrid.getChildren().clear();
        for (GridPaneState state : stateList) {
            Label label = new Label(state.content()); // Create a new node with the saved content
            workoutGrid.add(label, state.column(), state.row());
        }
    }


}
