package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class WorkoutViewController {
    @FXML
    private Label nameLabel;
    @FXML
    private ListView<String> exerciseListView;

    public void initialize(Workout workout){
        nameLabel.setText("Name: " + workout.getName());
        for (Exercise exercise : workout.getExercises()) {
            exerciseListView.getItems().add(exercise.toString());
        }
    }
}
