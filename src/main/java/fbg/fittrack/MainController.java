package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button homeButton;
    @FXML
    private Button scheduleButton;
    @FXML
    private Button workoutButton;
    @FXML
    private Button progressButton;
    @FXML
    private Button settingsButton;
    @FXML
    private AnchorPane contentArea;

    @FXML
    protected void onHomeButtonClick() throws IOException {
        // display home page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent homeContent = fxmlLoader.load();
        contentArea.getChildren().setAll(homeContent);
    }
    @FXML
    protected void onScheduleButtonClick() throws IOException {
        // display schedule page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("schedule.fxml"));
        Parent scheduleContent = fxmlLoader.load();
        contentArea.getChildren().setAll(scheduleContent);
    }
    @FXML
    protected void onWorkoutButtonClick() throws IOException {
        // display workout page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workout.fxml"));
        Parent workoutContent = fxmlLoader.load();
        contentArea.getChildren().setAll(workoutContent);
    }
    @FXML
    protected void onProgressButtonClick() throws IOException {
        // display progress page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("progress.fxml"));
        Parent progressContent = fxmlLoader.load();
        contentArea.getChildren().setAll(progressContent);
    }
    @FXML
    protected void onSettingsButtonClick() {
        // display settings popup
        settingsButton.getContextMenu().show(settingsButton, Side.BOTTOM, 0, 0);
    }
}
