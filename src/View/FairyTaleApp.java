package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class that will start the GUI
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class FairyTaleApp extends Application {
    /**
     * An abstract method which automatically calls the launch
     * @param window setting the stage
     * @throws IOException exception thrown if an IO error occurs
     */
    public void start(Stage window) throws IOException
    {
        window.setTitle("Fairy Tale Lib");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FairyTaleGUI.fxml"));
        Scene scene = new Scene(loader.load());
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
}