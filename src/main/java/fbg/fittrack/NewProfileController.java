package fbg.fittrack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class NewProfileController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField goalTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Button saveButton;

    @FXML
    protected void onSaveButtonClick() throws Exception {
        User user = new User();
        user.setName(nameTextField.getText());
        user.setGoal(goalTextField.getText());
        user.setWeight(Double.parseDouble(weightTextField.getText()));
        user.setHeight(Double.parseDouble(heightTextField.getText()));
        user.saveProfile();
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        Main.restart();

    }



}
