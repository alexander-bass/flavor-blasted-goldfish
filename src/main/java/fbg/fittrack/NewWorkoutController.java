package fbg.fittrack;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewWorkoutController {
    @FXML
    private TextField nameTextField;
    @FXML
    private Button okButton;
    @FXML
    private VBox exerciseList;
    private final List<CheckBox> checkBoxList = new ArrayList<>();
    private final List<Exercise> selectedExercises = new ArrayList<>();
    private final ObservableList<Exercise> exerciseObservableList = FXCollections.observableArrayList();
    private ObservableList<Workout> workoutObservableList;

    @FXML
    protected void onOkButtonClick() {
        User user = User.loadProfile(new File("userProfile.json"));
        for (CheckBox cb : checkBoxList) {
            if (cb.isSelected()) {
                Exercise exercise = (Exercise) cb.getUserData();
                selectedExercises.add(exercise);
            }
        }
        Workout newWorkout = new Workout(nameTextField.getText(), selectedExercises);
        user.addWorkout(newWorkout);
        user.saveProfile();
        workoutObservableList.add(newWorkout);
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onAddExerciseButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newExercise.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        NewExerciseController nec = fxmlLoader.getController();
        nec.setExercisesList(exerciseObservableList);

        // again why????
        stage.setMinWidth(600);
        stage.setMinHeight(450);

        stage.setScene(scene);
        stage.show();
    }

    public void initialize(User user) throws IOException {
        if (user.getExercises() != null) {
            for (Exercise exercise : user.getExercises()) {
                exerciseObservableList.add(exercise);
                addExerciseToVBox(exercise);
            }

            // Listener for changes in the exercises list
            exerciseObservableList.addListener((ListChangeListener.Change<? extends Exercise> c) -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (Exercise newExercise : c.getAddedSubList()) {
                            try {
                                addExerciseToVBox(newExercise);
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

    private void addExerciseToVBox (Exercise exercise) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseItem.fxml"));
        Node exerciseItem = loader.load();

        ExerciseItemController eic = loader.getController();
        eic.setExerciseInfo(exercise);
        eic.getExerciseInfo().setUserData(exercise);
        checkBoxList.add(eic.getExerciseInfo());
        exerciseList.getChildren().add(exerciseItem);
    }

    public void setWorkoutObservableList(ObservableList<Workout> workouts) {
        this.workoutObservableList = workouts;
    }
}
