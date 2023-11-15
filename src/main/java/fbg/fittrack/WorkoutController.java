package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
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
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newWorkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        NewWorkoutController nwc = fxmlLoader.getController();
        nwc.createList(User.loadProfile(new File("userProfile.json")));

        stage.setScene(scene);
        stage.show();

    }

    public void initialize(User user) throws IOException {
        int numRows = workoutGrid.getRowConstraints().size();
        int numCols = workoutGrid.getColumnConstraints().size();
        int index = 0;

        for (Workout workout : user.getWorkouts()) {
            int row = index/numCols;
            int column = index%numCols;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutItem.fxml"));
            Node workoutItem = loader.load();

            WorkoutItemController wic = loader.getController();
            wic.setWorkoutNameLabel(workout);

            workoutGrid.add(workoutItem, column, row);
            index++;
        }
    }


}
