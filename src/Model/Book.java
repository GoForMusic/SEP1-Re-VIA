package Model;

import java.io.Serializable;

public class Book extends Item implements Serializable {
    private String ISBN;

    public Book(String title, String author, String ISBN)
    {
        super(title, author);
        this.ISBN=ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override public String getDetails() {
        return this.ISBN;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Book)) return false;

        Book book = (Book) obj;

        if(book.ISBN.equals(this.ISBN)&&super.equals(book)) return true;
        else return false;
    }
}
