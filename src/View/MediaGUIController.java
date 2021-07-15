package View;

import Model.*;
import Utils.FairyTaleModelManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class MediaGUIController {
    @FXML private ListView<HBox> listMedia;
    @FXML private DialogPane dialogPop;
    @FXML private TextField labelMediaTitle;
    @FXML private TextField labelMediaAuthor;
    @FXML private ComboBox<String> labelMediaType;
    @FXML private ComboBox<String> labelNewMedia;
    @FXML private TextField customerFirstName;
    @FXML private TextField customerLastName;
    @FXML private TextField customerEmail;
    @FXML private ComboBox<String> customerType;
    @FXML private ComboBox<String> customerTypeOfAction;
    @FXML private DatePicker customerDate;
    @FXML private ListView<HBox> customersAssign;
    @FXML private ListView<HBox> customersExceeded;
    @FXML private TextField searchTitle;

    private FairyTaleModelManager manager;
    private ArrayList<Item> items;
    private int itemIndex;


    public void initialize()
{
    labelMediaType.getItems().addAll("CD","DVD");
    labelMediaType.getSelectionModel().selectFirst();
    labelNewMedia.getItems().addAll("Yes","No");
    labelNewMedia.getSelectionModel().selectFirst();
    itemIndex=0;
    customerType.getItems().addAll("Student","Lecturer");
    customerTypeOfAction.getItems().addAll("Rent","Borrow");
    manager = new FairyTaleModelManager("items.bin","items.txt");
    customerDate.setValue(LocalDate.now());
    setListDetails(manager.getAllItemsMedia());
}

    private void setListDetails(ArrayList<Item> list)
    {
        items = manager.getAllItems();
        listMedia.getItems().clear();
        for(Item item:list)
        {
            HBox hBox = new HBox();
            //add the item title and author as well the specific details
            Label label = new Label("Title: "+item.getTitle() + "| Author: " + item.getAuthor()+" | "+ item.getDetails());
            label.setPrefWidth(500);
            hBox.getChildren().add(label);
            //add edit button
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");

            //edit item button event handler using lambda method
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialogPop.setVisible(true);
                labelMediaTitle.setText(item.getTitle());
                labelMediaAuthor.setText(item.getAuthor());
                labelMediaType.setValue(((Media)item).getType());
                labelNewMedia.setValue(((Media)item).isNewMedia()?"Yes":"No");
                label.setText(item.getDetails());
                itemIndex=items.indexOf(item);
                //Populate the list with all the customers that are assigned to the specific item
                refreshCustomerList(items.get(itemIndex));
            });

            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");
            //remove item button event handler using lambda method
            removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{

                //Ask for a confirmation to delete the item, if the OK button was pressed the deletion process can start and save into file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the media\n("+item.getTitle()+" by "+item.getAuthor()+") from the system?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK)
                {
                    manager.removeItem(item);
                    setListDetails(manager.getAllItemsMedia());
                }

            });
            HBox.setMargin(editButton,new Insets(0,5,0,0));
            hBox.getChildren().addAll(editButton,removeButton);
            listMedia.getItems().add(hBox);
        }
    }

    private void refreshCustomerList(Item item)
    {
        customersAssign.getItems().clear();
        customersExceeded.getItems().clear();

        ArrayList<Person> localList = item.getPersonList();

        for(Person person:localList)
        {
            customerGenerateList(person,item, customersAssign);
            if(person.getReturningDate().isBefore(MyDate.today()))
                customerGenerateList(person,item,customersExceeded);
        }
    }

    private void customerGenerateList(Person person,Item item, ListView<HBox>  customerList)
    {
        HBox hBox = new HBox();
        //add the item title and author as well the specific details
        Label label = new Label(person.getFirstName() +" "+person.getLastName()+" | email: "+ person.getEmail()+"\n Type of customer: " + person.getType()+" | Type of Action: "+person.getTypeOfAction()+"\n Returning date on: "+person.getReturningDate());
        hBox.getChildren().add(label);

        //add Remove button
        Button removeButton = new Button("Remove");
        removeButton.getStyleClass().add("buttonRed");
        //remove customer button event handler using lambda method
        removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)-> {
            //Ask for a confirmation to delete the customer, if the OK button was pressed the deletion process can start and save into file
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the assigned customer\n(" + person.getFirstName() + " " + person.getLastName() + ") from the system?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                item.removeAPerson(person);
                manager.editItem(item,itemIndex);
                refreshCustomerList(item);
            }

        });

        //Add a little space between customer details and remove button
        HBox.setMargin(removeButton,new Insets(0,0,0,20));
        hBox.getChildren().addAll(removeButton);
        customerList.getItems().add(hBox);

    }

    @FXML
    void assignCustomer(MouseEvent event) {
        if(customerEmail.getText().isEmpty()&&customerFirstName.getText().isEmpty()&&customerLastName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {
            MyDate localDate = new MyDate(customerDate.getValue().getDayOfMonth(), customerDate.getValue().getMonthValue(), customerDate.getValue().getYear());
            Person localPerson = new Person(customerFirstName.getText(), customerLastName.getText(), customerEmail.getText(), customerType.getValue());
            Item localItem = items.get(itemIndex);
            localPerson.setTypeOfAction(customerTypeOfAction.getValue());
            localPerson.setReturningDate(localDate);
            localItem.assignAPerson(localPerson);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The customer has been added to the item!");
            alert.showAndWait();
            manager.editItem(localItem,itemIndex);
            refreshCustomerList(localItem);
        }
    }

    @FXML
    void cancelProcess(MouseEvent event) {
        dialogPop.setVisible(false);
    }

    @FXML
    void saveProcess(MouseEvent event) {

        if(labelMediaTitle.getText().isEmpty()&&labelMediaAuthor.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {

           Item item = new Media(labelMediaTitle.getText(), labelMediaAuthor.getText(),labelMediaType.getValue(),labelNewMedia.getValue().equals("Yes")?true:false);

            //edit the object on the specific index who will be stored in the lambda function
            manager.editItem(item,itemIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The media new details has been saved!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the book list with the new changes
            setListDetails(manager.getAllItemsMedia());
            cancelProcess(event);
        }
    }

    @FXML void searchItem(MouseEvent event){
        setListDetails(manager.getAllItemsByTitle(searchTitle.getText()));
    }

}