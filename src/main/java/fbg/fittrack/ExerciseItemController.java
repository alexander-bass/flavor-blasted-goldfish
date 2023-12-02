package fbg.fittrack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ExerciseItemController {

    @FXML
    private CheckBox exerciseInfo;
    @FXML
    private Button exerciseSettingsButton;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    private Exercise exercise;
    private ObservableList<Exercise> exerciseObservableList;

    public void setExercisesList(ObservableList<Exercise> exercises) {
        this.exerciseObservableList = exercises;
    }

    public void setExerciseInfo(Exercise exercise) {
        exerciseInfo.setText(exercise.getName() + ": " + exercise.getSets() + "x" + exercise.getReps() + ", " + exercise.getWeight() + "lbs");
    }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public CheckBox getExerciseInfo() {
        return exerciseInfo;
    }

    @FXML
    protected void onExerciseSettingsButtonClick() {
        exerciseSettingsButton.getContextMenu().show(exerciseSettingsButton, Side.BOTTOM, 0, 0);
    }
    @FXML
    protected void onEditMenuItemClick() throws IOException {
        // open up the newExercise window but with TextFields filled with current exercise information
        // user can type in new information and submit, just like newExercise logic
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newExercise.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        NewExerciseController nec = fxmlLoader.getController();
        nec.setExercisesList(exerciseObservableList);
        nec.setTextFields(exercise);
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onDeleteMenuItemClick() throws IOException {
        // remove from user exercise list
        User user = User.loadProfile(new File("userProfile.json"));
        user.removeExercise(exercise);
        user.saveProfile();

        // remove from observable list
        exerciseObservableList.remove(exercise);
    }

    public Exercise getExercise() {
        return exercise;
    }
}
