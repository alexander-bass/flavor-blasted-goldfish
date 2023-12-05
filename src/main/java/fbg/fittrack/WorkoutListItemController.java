package fbg.fittrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class WorkoutListItemController {
    @FXML
    private CheckBox workoutInfo;
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Workout workout;


    public void setWorkoutInfo(Workout workout) {
        workoutInfo.setText(workout.getName() + ": " + workout.getExercises());
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
