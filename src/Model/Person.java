package Model;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String type;
    private String typeOfAction;
    private MyDate returningDate;

    public Person(String firstName, String lastName, String email, String type)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.typeOfAction = "";
        this.returningDate = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public MyDate getReturningDate() {
        return returningDate;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReturningDate(MyDate returningDate) {
        this.returningDate = returningDate.copy();
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person)) return false;

        Person person = (Person) obj;

        if(this.firstName.equals(person.firstName)&&this.lastName.equals(person.lastName)&&this.email.equals(person.email)&&this.type.equals(person.type)&&this.typeOfAction.equals(person.typeOfAction)&&this.returningDate.equals(person.returningDate)) return true;
        else return false;
    }
}
