package View;

import Model.Article;
import Model.Book;
import Model.Item;
import Model.Media;
import Utils.FairyTaleModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * A class that will be used as a controller for GUI
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
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
    @FXML private AnchorPane tabMedia;
    @FXML private TextField itemTitle;
    @FXML private TextField itemAuthor;

    private FairyTaleModelManager manager;

    /**
     * This method will initialize the data when the interface was open.
     * */
     public void initialize() throws IOException {
         manager = new FairyTaleModelManager("items.bin", "items.txt");
         initializeTabs();
        mediaType.getItems().addAll("Book","Article","CD/DVD");
        mediaCDType.getItems().addAll("CD","DVD");
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
        tabArticles.getChildren().add(new FXMLLoader().load(getClass().getResource("ArticleGUI.fxml")));
        tabMedia.getChildren().add(new FXMLLoader().load(getClass().getResource("MediaGUI.fxml")));
    }

    /**
     * A method that will show-up the add item view in the GUI
     */
    @FXML void addItem(MouseEvent event) {
        addItemPanel.setVisible(true);
    }

    /**
     * A method that will close the add item view in the GUI and reset all the fields
     */
    @FXML void cancelNewItemProcess(MouseEvent event) {
        addItemPanel.setVisible(false);
        mediaType.getSelectionModel().selectFirst();
        mediaCDType.getSelectionModel().selectFirst();
        mediaNewMedia.getSelectionModel().selectFirst();
        typeOfItemLabelVarialbe.setText("");
        mediaCDDVD.setText("");
        typeOfItemTextFieldVariable.setText("");
    }

    /**
     * A method that will save the new item into the system
     */
    @FXML void storeNewItem(MouseEvent event) throws IOException {

       if(itemTitle.getText().isEmpty()&&itemAuthor.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
           alert.showAndWait();
       }
        else {

           if (mediaType.getValue().equals("Book")) {
               Item item = new Book(itemTitle.getText(), itemAuthor.getText(), typeOfItemTextFieldVariable.getText());
               manager.saveItem(item);
           } else if (mediaType.getValue().equals("Article")) {
               Item item = new Article(itemTitle.getText(), itemAuthor.getText(), typeOfItemTextFieldVariable.getText());
               manager.saveItem(item);
           } else if (mediaType.getValue().equals("CD/DVD")) {
               Item item = new Media(itemTitle.getText(), itemAuthor.getText(), mediaCDType.getValue(), mediaNewMedia.getValue().equals("Yes")?true:false);
               manager.saveItem(item);
           }
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "The item has been added!");
           alert.showAndWait();
           cancelNewItemProcess(event);
           initializeTabs();
       }
    }

    /**
     * A method that will set-up some specific fields base on the type of Item
     */
    @FXML private void comboAction(ActionEvent event) {

        if(mediaType.getValue().equals("Book"))
        {
            typeOfItemLabelVarialbe.setText("ISBN:");
            typeOfItemLabelVarialbe.setVisible(true);
            typeOfItemTextFieldVariable.setVisible(true);
            mediaCDType.setVisible(false);
            mediaNewMedia.setVisible(false);
            mediaCDDVD.setVisible(false);
        }else if(mediaType.getValue().equals("Article"))
        {
            typeOfItemLabelVarialbe.setText("Magazine:");
            typeOfItemLabelVarialbe.setVisible(true);
            typeOfItemTextFieldVariable.setVisible(true);
            mediaCDType.setVisible(false);
            mediaNewMedia.setVisible(false);
            mediaCDDVD.setVisible(false);
        }else if(mediaType.getValue().equals("CD/DVD"))
        {
            typeOfItemLabelVarialbe.setText("Media CD/DVD:");
            typeOfItemLabelVarialbe.setVisible(true);
            mediaCDType.setVisible(true);
            mediaCDDVD.setVisible(true);
            mediaNewMedia.setVisible(true);
            typeOfItemTextFieldVariable.setVisible(false);
        }

    }

    /**
     * A method that will import all the data from the txt file and stored in the binary file
     */
    @FXML private void uploadData(MouseEvent event) throws IOException {
        manager.saveDataFromTxt();
        initialize();
    }

}
