package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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


    @FXML
    protected void onOkButtonClick() {
        User user = User.loadProfile(new File("userProfile.json"));
        user.addExercise(new Exercise(nameTextField.getText(), Integer.parseInt(setsTextField.getText()), Integer.parseInt(repsTextField.getText()), Double.parseDouble(weightTextField.getText())));
        user.saveProfile();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
