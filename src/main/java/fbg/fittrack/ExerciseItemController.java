package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class ExerciseItemController {

    @FXML
    private CheckBox exerciseInfo;

    public void setExerciseInfo(Exercise exercise) {
        exerciseInfo.setText(exercise.getName() + ": " + exercise.getSets() + "x" + exercise.getReps() + ", " + exercise.getWeight() + "lbs");
    }

    public CheckBox getExerciseInfo() {
        return exerciseInfo;
    }
}
