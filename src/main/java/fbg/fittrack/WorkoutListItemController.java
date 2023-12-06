package fbg.fittrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class WorkoutListItemController {
    @FXML
    private Label workoutLabel;
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Workout workout;


    public void setWorkoutInfo(Workout workout) {
        workoutLabel.setText(workout.getName() + ": " + workout.getExercises());
        choiceBox.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) workout.setDay(newVal);
        });

    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

}
