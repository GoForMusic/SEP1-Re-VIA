package Model;

import java.io.Serializable;

/**
 * A class that include all the information about the customer
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private String typeOfAction;
    private MyDate returningDate;

    /**
     * A 4 argument constructor
     * @param firstName set the customer first name
     * @param lastName set the customer last name
     * @param email set the customer email address
     * @param type set the type of the customer
     * typeOfAction and returningDate will be null;
     */
    public Person(String firstName, String lastName, String email, String type)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.typeOfAction = "";
        this.returningDate = null;
    }

    /**
     * A get method that will return the customer's fist name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * A get method that will return the customer's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * A get method that will return the customer's email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * A get method that will return the type of the customer
     * @return customer's type
     */
    public String getType() {
        return type;
    }

    /**
     * A get method that will return the retuning date for an Item
     * @return returning date
     */
    public MyDate getReturningDate() {
        return returningDate;
    }

    /**
     * A get method that will return the type of action
     * @return type of action
     */
    public String getTypeOfAction() {
        return typeOfAction;
    }

    /**
     * A set method that will set the customer's first name
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * A set method that will set the customer's last name
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * A set method that will set the customer's email address
     * @param email new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A set method that will set the customer's type
     * @param type set type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A set method that will set the returning date
     * @param returningDate set returning date
     */
    public void setReturningDate(MyDate returningDate) {
        this.returningDate = returningDate.copy();
    }

    /**
     * A set method that will set the type of action
     * @param typeOfAction set type of action
     */
    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    /**
     * A method that will verify if another object is equal with Person
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person)) return false;

        Person person = (Person) obj;

        if(this.firstName.equals(person.firstName)&&this.lastName.equals(person.lastName)&&this.email.equals(person.email)&&this.type.equals(person.type)&&this.typeOfAction.equals(person.typeOfAction)&&this.returningDate.equals(person.returningDate)) return true;
        else return false;
    }
}
