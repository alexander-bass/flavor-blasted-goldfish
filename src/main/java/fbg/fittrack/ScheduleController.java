package fbg.fittrack;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ScheduleController {
    @FXML
    private VBox sundayPanel;
    @FXML
    private VBox mondayPanel;
    @FXML
    private VBox tuesdayPanel;
    @FXML
    private VBox wednesdayPanel;
    @FXML
    private VBox thursdayPanel;
    @FXML
    private VBox fridayPanel;
    @FXML
    private VBox saturdayPanel;
    private final ObservableList<Workout> scheduleObservableList = FXCollections.observableArrayList();

    public void initialize(User user) throws IOException {

        if (user.getSchedule() != null) {
            for (Workout workout : user.getSchedule()) {
                scheduleObservableList.add(workout);
                assignVBox(workout);
            }
        }

        // listener
        scheduleObservableList.addListener((ListChangeListener.Change<? extends Workout> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Workout workout : c.getAddedSubList()) {
                        try {
                            assignVBox(workout);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (c.wasRemoved()) {
                    for (Workout removedWorkout : c.getRemoved()) {
                        // Handle the removal of each removed exercise
                        removeWorkoutFromVBox(removedWorkout);
                    }
                }
            }
        });

    }

    private void assignVBox(Workout workout) throws IOException {
        switch (workout.getDay()) {
            case "Monday" -> addWorkoutToVBox(workout, mondayPanel);
            case "Tuesday" -> addWorkoutToVBox(workout, tuesdayPanel);
            case "Wednesday" -> addWorkoutToVBox(workout, wednesdayPanel);
            case "Thursday" -> addWorkoutToVBox(workout, thursdayPanel);
            case "Friday" -> addWorkoutToVBox(workout, fridayPanel);
            case "Saturday" -> addWorkoutToVBox(workout, saturdayPanel);
            default -> addWorkoutToVBox(workout, sundayPanel);
        }
    }

    @FXML
    private void onScheduleSettingsButtonClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newSchedule.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        NewScheduleController nsc = fxmlLoader.getController();
        nsc.initialize(User.loadProfile(new File("userProfile.json")));
        nsc.setScheduleObservableList(scheduleObservableList);

        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.setScene(scene);
        stage.show();
    }

    private void addWorkoutToVBox(Workout workout, VBox panel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutItem.fxml"));
        Node workoutItem = loader.load();
        workoutItem.setUserData(workout);

        WorkoutItemController wic = loader.getController();
        wic.hideMenuItems();
        wic.setWorkoutNameLabel(workout);
        wic.setWorkout(workout);
        wic.setScheduleObservableList(scheduleObservableList);

        panel.getChildren().add(workoutItem);
    }

    private void removeWorkoutFromVBox(Workout workout) {
        switch (workout.getDay()) {
            case "Monday" -> {
                mondayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            case "Tuesday" -> {
                tuesdayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            case "Wednesday" -> {
                wednesdayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            case "Thursday" -> {
                thursdayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            case "Friday" -> {
                fridayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            case "Saturday" -> {
                saturdayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
            default -> {
                sundayPanel.getChildren().removeIf(child -> child.getUserData() == workout);
            }
        }

    }

}
