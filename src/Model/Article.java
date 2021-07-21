package Model;

import java.io.Serializable;

/**
 * A sub-class that include all the information about the Article
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Article extends Item implements Serializable {
    private String magazine;

    /**
     * A 3 argument constructor
     * @param title the title of the Article
     * @param author the author of the Article
     * @param magazine the magazine name of the Article
     */
    public Article(String title, String author, String magazine)
    {
        super(title, author);
        this.magazine=magazine;
    }

    /**
     * A get method that will return the magazine name
     * @return magazine name
     */
    public String getMagazine() {
        return magazine;
    }

    /**
     * A set method that will set the new name for magazine
     * @param magazine
     */
    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    /**
     * A get method override from the Item abstract class that will return details about the Article
     * @return the magazine name
     */
    @Override
    public String getDetails() {
        return this.magazine;
    }

    /**
     * A method that will verify if another object is equal with Article
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Article)) return false;

        Article article = (Article) obj;

        if(article.magazine.equals(this.magazine)&&super.equals(article)) return true;
        else return false;
    }
}
