package Model;

import java.io.Serializable;

/**
 * A sub-class that include all the information about the Media (CV/DVD)
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Media extends Item implements Serializable {
    private String type;
    private boolean newMedia;

    /**
     * A 4 argument constructor
     * @param title the title of the Media
     * @param author the author of the Media
     * @param type the type of the Media
     * @param newMedia if is new Media or an existing media
     */
    public Media(String title, String author, String type, boolean newMedia)
    {
        super(title, author);
        this.type=type;
        this.newMedia=newMedia;
    }

    /**
     * A get method that will return the type of Media
     * @return the type of Media
     */
    public String getType() {
        return type;
    }

    /**
     * A get method that will return if is new Media or not
     * @return true or false if is new Media
     */
    public boolean isNewMedia() {
        return newMedia;
    }

    /**
     * A set method that will set the type of the Media
     * @param type new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A set method that will change the status of media with the new one
     * @param newMediaStatus new media status
     */
    public void changeStatusOfMedia( boolean newMediaStatus){
        this.newMedia=newMediaStatus;
    }

    /**
     * A get method override from the Item abstract class that will return details about the Media
     * @return the type and if is new media or not
     */
    @Override
    public String getDetails() {
        return "Media type: "+ this.type+"\nNew media Added?("+this.newMedia+")";
    }

    /**
     * A method that will verify if another object is equal with Media
     * @param obj the another obj
     * @return true if everything is matching otherwise false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Media)) return false;

        Media media = (Media) obj;

        if(media.type.equals(this.type)&&media.newMedia==this.newMedia&&super.equals(media)) return true;
        else return false;
    }
}
