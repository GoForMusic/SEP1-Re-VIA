package View;

import Model.Book;
import Model.Item;
import Model.MyDate;
import Model.Person;
import Utils.FairyTaleModelManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class BookGUIController {
    @FXML private ListView<HBox> listBooks;
    @FXML private DialogPane dialogPop;
    @FXML private TextField labelBookTitle;
    @FXML private TextField labelBookAuthor;
    @FXML private TextField labelBookISBN;
    @FXML private TextField customerFirstName;
    @FXML private TextField customerLastName;
    @FXML private TextField customerEmail;
    @FXML private ComboBox<String> customerType;
    @FXML private ComboBox<String> customerTypeOfAction;
    @FXML private DatePicker customerDate;
    @FXML private ListView<HBox> customersAssign;
    @FXML private ListView<HBox> customersExceeded;


    private FairyTaleModelManager manager;
    private ArrayList<Item> items;
    private int itemIndex;
    private Instant LocalData;


    public void initialize()
{
    itemIndex=0;
    customerType.getItems().addAll("Student","Lecturer");
    customerTypeOfAction.getItems().addAll("Rent","Barrow");
    manager = new FairyTaleModelManager("items.bin","items.txt");
    customerDate.setValue(LocalDate.now());
    setListDetails(manager.getAllItemsBook());
}

    private void setListDetails(ArrayList<Item> list)
    {
        listBooks.getItems().clear();
        items = list;

        for(Item item:items)
        {
            HBox hBox = new HBox();
            //add the item title and author as well the specific details
            Label label = new Label("Title: "+item.getTitle() + "| Author: " + item.getAuthor()+" | ISBN: "+ item.getDetails());
            label.setPrefWidth(500);
            hBox.getChildren().add(label);
            //add edit button
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");

            //edit item button event handler using lambda method
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialogPop.setVisible(true);
                labelBookTitle.setText(item.getTitle());
                labelBookAuthor.setText(item.getAuthor());
                labelBookISBN.setText(item.getDetails());
                itemIndex=items.indexOf(item);
                //Populate the list with all the customers that are assigned to the specific item
                refreshCustomerList(item);
            });

            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");
            //remove item button event handler using lambda method
            removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{

                //Ask for a confirmation to delete the item, if the OK button was pressed the deletion process can start and save into file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the book\n("+item.getTitle()+" by "+item.getAuthor()+") from the system?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK)
                {
                    manager.removeItem(item);
                    setListDetails(manager.getAllItemsBook());
                }

            });
            HBox.setMargin(editButton,new Insets(0,5,0,0));
            hBox.getChildren().addAll(editButton,removeButton);
            listBooks.getItems().add(hBox);
        }
    }

    private void refreshCustomerList(Item item)
    {
        customersAssign.getItems().clear();

        for(Person person:item.getPersonList())
        {
            HBox hBox = new HBox();
            //add the item title and author as well the specific details
            Label label = new Label(person.getFirstName() +" "+person.getLastName()+" | email: "+ person.getEmail()+"\n Type of customer: " + person.getType()+" | Type of Action: "+person.getTypeOfAction()+"\n Returning date on: "+person.getReturningDate());
            hBox.getChildren().add(label);

            //add Remove button
            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");
            //remove customer button event handler using lambda method
            removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{

                //Ask for a confirmation to delete the customer, if the OK button was pressed the deletion process can start and save into file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the assigned customer\n("+person.getFirstName()+" "+person.getLastName()+") from the system?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK)
                {
                    item.removeAPerson(person);
                    refreshCustomerList(item);
                }

            });

            //Add a little space between customer details and remove button
            HBox.setMargin(removeButton,new Insets(0,0,0,20));
            hBox.getChildren().addAll(removeButton);
            customersAssign.getItems().add(hBox);
        }
    }

    @FXML
    void assignCustomer(MouseEvent event) {
        MyDate localDate = new MyDate(customerDate.getValue().getDayOfMonth(),customerDate.getValue().getMonthValue(),customerDate.getValue().getDayOfYear());
        Person localPerson = new Person(customerFirstName.getText(),customerLastName.getText(),customerEmail.getText(),customerType.getValue());
        Item localItem = items.get(itemIndex);
        localPerson.setTypeOfAction(customerTypeOfAction.getValue());
        localPerson.setReturningDate(localDate);
        localItem.assignAPerson(localPerson);
        refreshCustomerList(localItem);

    }

    @FXML
    void cancelProcess(MouseEvent event) {
        dialogPop.setVisible(false);
    }

    @FXML
    void saveProcess(MouseEvent event) {
        Item item = new Book(labelBookTitle.getText(),labelBookAuthor.getText(),labelBookISBN.getText());

        //edit the object on the specific index who will be stored in the lambda function
        manager.editItem(item,itemIndex);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The book new details has been saved!");
        alert.showAndWait();
        dialogPop.setVisible(false);
        //update the book list with the new changes
        setListDetails(manager.getAllItemsBook());
        cancelProcess(event);
    }

}
