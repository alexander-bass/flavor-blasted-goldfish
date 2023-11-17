package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;

public class ExerciseItemController {

    @FXML
    private CheckBox exerciseInfo;
    @FXML
    private Button exerciseSettingsButton;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem deleteMenuItem;

    public void setExerciseInfo(Exercise exercise) {
        exerciseInfo.setText(exercise.getName() + ": " + exercise.getSets() + "x" + exercise.getReps() + ", " + exercise.getWeight() + "lbs");
    }

    public CheckBox getExerciseInfo() {
        return exerciseInfo;
    }

    @FXML
    protected void onExerciseSettingsButtonClick() {
        exerciseSettingsButton.getContextMenu().show(exerciseSettingsButton, Side.BOTTOM, 0, 0);
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
