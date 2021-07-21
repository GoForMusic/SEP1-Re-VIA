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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A class that will be used as a controller for GUI
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
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
    @FXML private TextField searchTitle;


    private FairyTaleModelManager manager;
    private ArrayList<Item> items;
    private int itemIndex;

    /**
     * A method that will initialize all the date when the page will be loaded
     */
    public void initialize()
    {
    itemIndex=0;
    customerType.getItems().addAll("Student","Lecturer");
    customerTypeOfAction.getItems().addAll("Rent","Borrow");
    manager = new FairyTaleModelManager("items.bin", "items.txt");
    customerDate.setValue(LocalDate.now());
    setListDetails(manager.getAllItemsBook());
    }

    /**
     * A method that will prepare the list view with all the Book items
     * @param list the list of items
     */
    private void setListDetails(ArrayList<Item> list)
    {
        items = manager.getAllItems();
        listBooks.getItems().clear();

        for(Item item:list)
        {
            HBox hBox = new HBox();
            //add the item title and author as well the specific details
            Label label = new Label("Title: "+item.getTitle() + " | Author: " + item.getAuthor()+"| ISBN: "+ item.getDetails());
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
                refreshCustomerList(items.get(itemIndex));
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

    /**
     * A method that will prepare the list of customer for a specific item
     * @param item the specific item
     */
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

    /**
     * A method that will set-up a list with all the customers assigned to the item and a second list with specific customer where the returning date has exceeded
     * @param person the person details
     * @param item the item object where the customer is assigned
     * @param customerList reference to the specific view list
     */
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

    /**
     * A method that will assign a customer to the item
     */
    @FXML void assignCustomer(MouseEvent event) {
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

    /**
     * A method that will close the view of set new details for an item
     */
    @FXML void cancelProcess(MouseEvent event) {
        dialogPop.setVisible(false);
    }

    /**
     * A method that will save all the new data for an item on binary file
     */
    @FXML void saveProcess(MouseEvent event) {

        if(labelBookTitle.getText().isEmpty()&&labelBookAuthor.getText().isEmpty()&&labelBookISBN.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {

            Item item = new Book(labelBookTitle.getText(), labelBookAuthor.getText(), labelBookISBN.getText());

            //edit the object on the specific index who will be stored in the lambda function
            manager.editItem(item, itemIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The book new details has been saved!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the book list with the new changes
            setListDetails(manager.getAllItemsBook());
            cancelProcess(event);
        }
    }

    /**
     * A method that will generate a new list of items bases on the title
     */
    @FXML void searchItem(MouseEvent event){
        setListDetails(manager.getAllItemsByTitle(searchTitle.getText()));
    }


}
