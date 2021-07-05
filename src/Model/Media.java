package Model;

import java.io.Serializable;

public class Media extends Item implements Serializable {
    private String type;
    private boolean newMedia;

    public Media(String title, String author, String type, boolean newMedia)
    {
        super(title, author);
        this.type=type;
        this.newMedia=newMedia;
    }

    public String getType() {
        return type;
    }

    public boolean isNewMedia() {
        return newMedia;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void changeStatusOfMedia( boolean newMediaStatus){
        this.newMedia=newMediaStatus;
    }

    @Override
    public String getDetails() {
        return "Media type: "+ this.type+"\nNew media Added?("+this.newMedia+")";
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Media)) return false;

        Media media = (Media) obj;

        if(media.type.equals(this.type)&&media.newMedia==this.newMedia&&super.equals(media)) return true;
        else return false;
    }
}
