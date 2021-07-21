package Model;

import java.util.ArrayList;

/**
 * A class that include a list with all the items
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class Library {
    private ArrayList<Item> items;

    /**
     * A no-argument constructor
     */
    public Library()
    {
        items = new ArrayList<Item>();
    }

    /**
     * A add method that will add an item to the list
     * @param item the specific item that will be added
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * A get method that will return the list with all the items
     * @return a list with all the items
     */
    public ArrayList<Item> getItems()
    {
        return items;
    }
}
