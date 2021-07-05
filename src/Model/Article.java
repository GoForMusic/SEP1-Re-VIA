package Model;

import java.io.Serializable;

public class Article extends Item implements Serializable {
    private String magazine;
    public Article(String title, String author, String magazine)
    {
        super(title, author);
        this.magazine=magazine;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    @Override
    public String getDetails() {
        return this.magazine;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Article)) return false;

        Article article = (Article) obj;

        if(article.magazine.equals(this.magazine)&&super.equals(article)) return true;
        else return false;
    }
}
