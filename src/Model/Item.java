package Model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Item implements Serializable {
    private String title;
    private String author;
    private ArrayList<Person> persons;

    public Item(String title, String author)
    {
        this.title=title;
        this.author=author;
        this.persons=new ArrayList<Person>();
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public abstract String getDetails();

    public void assignAPerson(Person person)
    {
            Person localPerson = person;
            persons.add(person);
    }

    public void removeAPerson(Person person)
    {
        if(persons.contains(person)){
            persons.remove(person);
        }
    }

    public ArrayList<Person> getPersonList()
    {
        return persons;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Item)) return false;

        Item item = (Item) obj;

        if(item.title.equals(this.title)&&item.author.equals(this.author))return true;
        else return false;
    }

}
