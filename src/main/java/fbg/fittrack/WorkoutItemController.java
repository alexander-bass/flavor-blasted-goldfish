package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class WorkoutItemController {
    @FXML
    private Label workoutNameLabel;
    @FXML
    private Button workoutSettingsButton;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem deleteMenuItem;

    public void setWorkoutNameLabel(Workout workout) {
        workoutNameLabel.setText(workout.getName());
    }

    @FXML
    protected void onWorkoutSettingsButtonClick() {
        workoutSettingsButton.getContextMenu().show(workoutSettingsButton, Side.BOTTOM, 0, 0);
    }

    @FXML
    protected void onEditMenuItemClick() {
        // open up the newExercise window but with TextFields filled with current exercise information
        // user can type in new information and submit, just like newExercise logic
    }
    @FXML
    protected void onDeleteMenuItemClick() {
        // remove from user exercise list
        // remove observable list
        // remove from checkbox list
    }

}
