package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An abstract class that include all the information about the Item
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public abstract class Item implements Serializable {
    private String title;
    private String author;
    private ArrayList<Person> persons;

    /**
     * A 2 argument conastructor
     * @param title the title of the Item
     * @param author the author of the Item
     */
    public Item(String title, String author)
    {
        this.title=title;
        this.author=author;
        this.persons=new ArrayList<Person>();
    }

    /**
     * A get method that will return the title
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * A get method that will return the author
     * @return author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * A set method that will set the title
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A set method that will set the author
     * @param author new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * An abstract get method that will return all the details from the sub-classes
     * @return details from a sub-class
     */
    public abstract String getDetails();

    /**
     * A method that will assign a person to an item
     * @param person the customer who will be assigned
     */
    public void assignAPerson(Person person)
    {
            persons.add(person);
    }

    /**
     * A method that will remove a person from the specific item
     * @param person remove the specific person
     */
    public void removeAPerson(Person person)
    {
        if(persons.contains(person)){
            persons.remove(person);
        }
    }

    /**
     * A get method that will return an Array list of Persons
     * @return a list of persons who are assigned to the item
     */
    public ArrayList<Person> getPersonList()
    {
        return persons;
    }

    /**
     * A method that will verify if another object is equal with Item
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Item)) return false;

        Item item = (Item) obj;

        if(item.title.equals(this.title)&&item.author.equals(this.author))return true;
        else return false;
    }

}
