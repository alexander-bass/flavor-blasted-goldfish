package fbg.fittrack;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class WorkoutController {
    @FXML
    private GridPane workoutGrid;
    private final ObservableList<Workout> workoutObservableList = FXCollections.observableArrayList();
    private int lastIndex;

    @FXML
    protected void onNewWorkoutButtonClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newWorkout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        NewWorkoutController nwc = fxmlLoader.getController();
        nwc.initialize(User.loadProfile(new File("userProfile.json")));
        nwc.setWorkoutObservableList(workoutObservableList);

        // why do I have to do this??? why is it not opening at the right size???? reeeee
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.setScene(scene);
        stage.show();

    }

    public void initialize(User user) throws IOException {
        int index = 0;

        if (user.getWorkouts() != null) {
            for (Workout workout : user.getWorkouts()) {
                workoutObservableList.add(workout);
                addWorkoutToGrid(workout, index);
                index++;
                lastIndex = index;
            }

            // Listener for changes in the exercises list
            workoutObservableList.addListener((ListChangeListener.Change<? extends Workout> c) -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (Workout newWorkout : c.getAddedSubList()) {
                            try {
                                addWorkoutToGrid(newWorkout, lastIndex);
                                lastIndex++;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    // Handle other types of changes if necessary (e.g., removals)
                }
            });


        }
    }

    private void addWorkoutToGrid(Workout workout, int index) throws IOException {
        int row = index / workoutGrid.getColumnConstraints().size();
        int column = index % workoutGrid.getColumnConstraints().size();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutItem.fxml"));
        Node workoutItem = loader.load();

        WorkoutItemController wic = loader.getController();
        wic.setWorkoutNameLabel(workout);

        workoutGrid.add(workoutItem, column, row);
    }

}
