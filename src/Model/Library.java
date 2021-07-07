package Model;

import java.util.ArrayList;

public class Library {
    private ArrayList<Item> items;
    public Library()
    {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }
}
