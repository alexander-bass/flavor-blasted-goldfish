package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkoutItemController {
    @FXML
    private Label workoutNameLabel;

    public void setWorkoutNameLabel(Workout workout) {
        workoutNameLabel.setText(workout.getName());
    }

}
