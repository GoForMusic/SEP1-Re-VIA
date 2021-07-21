package Model;

import java.io.Serializable;

/**
 * A sub-class that include all the information about the Book
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Book extends Item implements Serializable {
    private String ISBN;

    /**
     * A 3 argument constructor
     * @param title the title of the Book
     * @param author the author of the Book
     * @param ISBN the ISBN of the Book
     */
    public Book(String title, String author, String ISBN)
    {
        super(title, author);
        this.ISBN=ISBN;
    }

    /**
     * A get method that will return the ISBN of the Book
     * @return ISBN of the book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * A set method that will set-up the ISBN of the book
     * @param ISBN new ISBN name
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * A get method override from the Item abstract class that will return details about the Book
     * @return the ISBN of the Book
     */
    @Override public String getDetails() {
        return this.ISBN;
    }

    /**
     * A method that will verify if another object is equal with Book
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Book)) return false;

        Book book = (Book) obj;

        if(book.ISBN.equals(this.ISBN)&&super.equals(book)) return true;
        else return false;
    }
}
