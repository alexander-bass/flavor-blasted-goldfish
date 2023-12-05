package fbg.fittrack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewScheduleController {
    @FXML
    private VBox workoutList;

    private ObservableList<Workout> scheduleObservableList;

    public void setScheduleObservableList(ObservableList<Workout> workouts) {
        this.scheduleObservableList = workouts;
    }
    @FXML
    private Button okButton;


    public void initialize(User user) throws IOException {
        if (user.getWorkouts() != null) {
            for (Workout workout : user.getWorkouts()) {
                addWorkoutToVBox(workout);
            }
        }
    }

    private void addWorkoutToVBox(Workout workout) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutListItem.fxml"));
        Node workoutListItem = loader.load();
        workoutListItem.setUserData(workout);

        WorkoutListItemController wlic = loader.getController();
        wlic.setWorkoutInfo(workout);
        wlic.setWorkout(workout);

        workoutList.getChildren().add(workoutListItem);
    }

    @FXML
    private void onOKButtonClick(){
        User user = User.loadProfile(new File("userProfile.json"));
        for(Node workoutListItem : workoutList.getChildren()) {
            Workout workout = (Workout) workoutListItem.getUserData();
            if (workout.getDay() != null){
                scheduleObservableList.add(workout);
                user.addToSchedule(workout);
            }
        }
        user.saveProfile();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

}
