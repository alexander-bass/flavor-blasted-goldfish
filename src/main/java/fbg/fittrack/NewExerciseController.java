package fbg.fittrack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class NewExerciseController {
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField repsTextField;
    @FXML
    private TextField setsTextField;
    @FXML
    private TextField weightTextField;
    private ObservableList<Exercise> exerciseObservableList;

    public void setExercisesList(ObservableList<Exercise> exercises) {
        this.exerciseObservableList = exercises;
    }


    @FXML
    protected void onOkButtonClick() {
        User user = User.loadProfile(new File("userProfile.json"));
        Exercise newExercise = new Exercise(nameTextField.getText(), Integer.parseInt(setsTextField.getText()), Integer.parseInt(repsTextField.getText()), Double.parseDouble(weightTextField.getText()));
        user.addExercise(newExercise);
        user.saveProfile();
        exerciseObservableList.add(newExercise);
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
