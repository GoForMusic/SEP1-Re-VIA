package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FairyTaleGUIController {
    @FXML private DialogPane addItemPanel;
    @FXML private Label typeOfItemLabelVarialbe;
    @FXML private Label mediaCDDVD;
    @FXML private ComboBox<String> mediaType;
    @FXML private ComboBox<String> mediaCDType;
    @FXML private ComboBox<String> mediaNewMedia;
    @FXML private TextField typeOfItemTextFieldVariable;
    @FXML private AnchorPane tabBooks;
    @FXML private AnchorPane tabArticles;
    @FXML private AnchorPane tabCDs;
    @FXML private AnchorPane tabDVDs;

    /**
     * This method will initialize the data when the interface was open.
     * */
     public void initialize() throws IOException {
        initializeTabs();
        mediaType.getItems().addAll("Book","Article","CD/DVD");
        mediaType.getSelectionModel().selectFirst();
        mediaCDType.getItems().addAll("CD","DV");
        mediaCDType.getSelectionModel().selectFirst();
        mediaNewMedia.getItems().addAll("Yes","No");
        mediaNewMedia.getSelectionModel().selectFirst();

    }

    /**
     * This method will pre-setup all the tabs on the interface and set-up other controllers to work
     * @throws IOException if they cannot load a specific fxml file
     */
    private void initializeTabs() throws IOException {
        tabBooks.getChildren().add(new FXMLLoader().load(getClass().getResource("BookGUI.fxml")));
        //tabArticles.getChildren().add(new FXMLLoader().load(getClass().getResource("BookGUI.fxml")));
        //tabCDs.getChildren().add(new FXMLLoader().load(getClass().getResource("BookGUI.fxml")));
        //tabDVDs.getChildren().add(new FXMLLoader().load(getClass().getResource("BookGUI.fxml")));
    }

    @FXML void addItem(MouseEvent event) {
        addItemPanel.setVisible(true);

    }

    @FXML
    void cancelNewItemProcess(MouseEvent event) {
        addItemPanel.setVisible(false);
        mediaType.getSelectionModel().selectFirst();
        mediaCDType.getSelectionModel().selectFirst();
        mediaNewMedia.getSelectionModel().selectFirst();
        typeOfItemLabelVarialbe.setText("");
        mediaCDDVD.setText("");
        typeOfItemTextFieldVariable.setText("");
    }

    @FXML
    void storeNewItem(MouseEvent event) {

    }
}
