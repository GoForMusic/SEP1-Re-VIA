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
import java.sql.Array;
import java.util.ArrayList;

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
    @FXML private TextField itemTitle;
    @FXML private TextField itemAuthor;

    private FairyTaleModelManager manager;

    /**
     * This method will initialize the data when the interface was open.
     * */
     public void initialize() throws IOException {
         manager = new FairyTaleModelManager("items.bin","items.txt");
         initializeTabs();
        mediaType.getItems().addAll("Book","Article","CD/DVD");
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
    void storeNewItem(MouseEvent event) throws IOException {

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
               boolean localBool;
               if (mediaNewMedia.getValue().equals("Yes")) localBool = true;
               else localBool = false;
               Item item = new Media(itemTitle.getText(), itemAuthor.getText(), mediaCDType.getValue(), localBool);
               manager.saveItem(item);
           }
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "The item has been added!");
           alert.showAndWait();
           cancelNewItemProcess(event);
           initializeTabs();
       }
    }

    @FXML
    private void comboAction(ActionEvent event) {

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
}
