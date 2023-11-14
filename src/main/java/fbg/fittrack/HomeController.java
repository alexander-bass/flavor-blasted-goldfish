package fbg.fittrack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label homeLabel;


    public void initialize(User user) { homeLabel.setText("Name: " + user.getName()); }


}
