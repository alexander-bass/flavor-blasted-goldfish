package fbg.fittrack;

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
    private Button addExerciseButton;
    @FXML
    private VBox exerciseList;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<Exercise> selectedExercises = new ArrayList<>();

    @FXML
    protected void onOkButtonClick() {
        User user = User.loadProfile(new File("userProfile.json"));
        for (CheckBox cb: checkBoxList){
            if (cb.isSelected()) {
                Exercise exercise = (Exercise) cb.getUserData();
                selectedExercises.add(exercise);
            }
        }
        user.addWorkout(new Workout(nameTextField.getText(), selectedExercises));
        user.saveProfile();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onAddExerciseButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newExercise.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void createList(User user) throws IOException {
        if (user.getExercises() != null) {
            for (Exercise exercise : user.getExercises()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseItem.fxml"));
                Node exerciseItem = loader.load();

                ExerciseItemController eic = loader.getController();
                eic.setExerciseInfo(exercise);
                eic.getExerciseInfo().setUserData(exercise);
                checkBoxList.add(eic.getExerciseInfo());

                exerciseList.getChildren().add(exerciseItem);
            }
        }
    }
}
