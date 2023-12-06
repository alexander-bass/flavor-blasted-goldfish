package fbg.fittrack;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class WorkoutItemController {
    @FXML
    private Label workoutNameLabel;
    @FXML
    private Button workoutSettingsButton;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem removeMenuItem;
    @FXML
    private MenuItem viewMenuItem;

    private Workout workout;
    private ObservableList<Workout> workoutObservableList;
    private ObservableList<Workout> scheduleObservableList;
    public void hideMenuItems() {
        editMenuItem.setVisible(false);
        deleteMenuItem.setVisible(false);
        removeMenuItem.setVisible(true);
    }

    public void setWorkoutNameLabel(Workout workout) {
        workoutNameLabel.setText(workout.getName());
    }

    public void setWorkoutList(ObservableList<Workout> workouts) {
        this.workoutObservableList = workouts;
    }
    public void setScheduleObservableList(ObservableList<Workout> workouts) {
        this.scheduleObservableList = workouts;
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
        User user = User.loadProfile(new File("userProfile.json"));
        user.removeWorkout(workout);
        user.removeFromSchedule(workout);
        user.saveProfile();

        if (workoutObservableList != null) {
            workoutObservableList.remove(workout);
        }
        if (scheduleObservableList != null) {
            scheduleObservableList.remove(workout);
        }

    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    @FXML
    protected void onRemoveMenuItemClick(){
        User user = User.loadProfile(new File("userProfile.json"));
        user.removeFromSchedule(workout);
        user.saveProfile();

        if (scheduleObservableList != null) {
            scheduleObservableList.remove(workout);
        }

    }

    @FXML
    protected void onViewMenuItemClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutView.fxml"));
        Parent root = loader.load();

        WorkoutViewController wvc = loader.getController();
        wvc.initialize(workout);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinHeight(275);
        stage.setMinWidth(250);
        stage.show();

    }


}
