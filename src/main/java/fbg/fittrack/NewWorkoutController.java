package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class NewWorkoutController {
    @FXML
    private TextField nameTextField;

    @FXML
    private Button okButton;
    @FXML
    private Button addExerciseButton;
    @FXML
    private VBox exerciseList;

    @FXML
    protected void onOkButtonClick() {
        User user = User.loadProfile(new File("userProfile.json"));
        user.addWorkout(new Workout(nameTextField.getText()));
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
        for (Exercise exercise : user.getExercises()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseItem.fxml"));
            Node exerciseItem = loader.load();

            ExerciseItemController eic = loader.getController();
            eic.setExerciseInfo(exercise);

            exerciseList.getChildren().add(exerciseItem);
        }
    }
}
