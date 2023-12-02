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
                    if (c.wasRemoved()) {
                        for (Workout removedWorkout : c.getRemoved()) {
                            // Handle the removal of each removed exercise
                            removeWorkoutFromGrid(removedWorkout);
                            lastIndex--;
                        }
                    }
                }
            });


        }
    }

    private void addWorkoutToGrid(Workout workout, int index) throws IOException {
        int row = index / workoutGrid.getColumnConstraints().size();
        int column = index % workoutGrid.getColumnConstraints().size();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("workoutItem.fxml"));
        Node workoutItem = loader.load();
        workoutItem.setUserData(workout);

        WorkoutItemController wic = loader.getController();
        wic.setWorkoutNameLabel(workout);
        wic.setWorkout(workout);
        wic.setWorkoutList(workoutObservableList);

        workoutGrid.add(workoutItem, column, row);
    }

    private void removeWorkoutFromGrid(Workout workout) {
        for (Node child : workoutGrid.getChildren()) {
            if (child.getUserData() == workout) {
                workoutGrid.getChildren().remove(child);
                reorderGrid(child);
                break;
            }
        }
    }

    private void reorderGrid(Node removed) {
        int maxColumns = workoutGrid.getColumnConstraints().size();
        int rowIndex = GridPane.getRowIndex(removed);
        int colIndex = GridPane.getColumnIndex(removed);
        for (Node node : workoutGrid.getChildren()) {
            int currentRow = GridPane.getRowIndex(node);
            int currentCol = GridPane.getColumnIndex(node);

            // Check if the node is after the removed item
            if (currentRow >= rowIndex && currentCol > colIndex) {
                // Shift to the left
                if (currentCol == 0) {
                    // Move to the previous row's last position if it's the first column
                    GridPane.setRowIndex(node, currentRow - 1);
                    GridPane.setColumnIndex(node, maxColumns - 1);
                } else {
                    // Move to the previous column
                    GridPane.setColumnIndex(node, currentCol - 1);
                }
            }

        }
    }

}
