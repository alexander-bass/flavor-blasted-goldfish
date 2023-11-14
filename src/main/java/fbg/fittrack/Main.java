package fbg.fittrack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // check for a user profile, if none is found, prompt to create.
        File userProfile = new File("userProfile.json");
        if(!userProfile.exists()) {
            // prompt user for information
            // this should reboot the program once saved, but currently just exits
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newProfile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.show();
        }

        else {
            User user = User.loadProfile(userProfile);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            MainController mc = fxmlLoader.getController();
            mc.initialize(user);
            stage.setScene(scene);
            stage.show();
        }
    }


    public static void main(String[] args) { launch(); }
}
