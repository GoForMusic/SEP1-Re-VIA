package Utils;

import Model.Article;
import Model.Book;
import Model.Item;
import Model.Media;

import java.util.ArrayList;

public class FairyTaleModelManager {
    private String objectFileName;
    private String textFileName;
    private MyFileHandler myFileHandler;

    public FairyTaleModelManager(String objectFileName, String textFileName)
    {
        this.objectFileName=objectFileName;
        this.textFileName=textFileName;
        this.myFileHandler=new MyFileHandler();
    }

    public ArrayList<Item> getAllItems()
    {
        ArrayList<Item> items = (ArrayList<Item>) myFileHandler.readObjectFromFile(objectFileName);
        return items;
    }

    public ArrayList<Item> getAllItemsByTitle(String title)
    {
        ArrayList<Item> temp = getAllItems();
        ArrayList<Item> finalArray = new ArrayList<Item>();
        for(Item index: temp)
        {
            if(index.getTitle().contains(title))
                finalArray.add(index);
        }

        return finalArray;
    }


    public ArrayList<Item> getAllItemsBook()
    {
        ArrayList<Item> temp = getAllItems();
        ArrayList<Item> finalArray = new ArrayList<Item>();
        for(Item index: temp)
        {
           if(index instanceof Book)
               finalArray.add(index);
        }
        return finalArray;
    }

    public ArrayList<Item> getAllItemsArticle()
    {
        ArrayList<Item> temp = getAllItems();
        ArrayList<Item> finalArray = new ArrayList<Item>();
        for(Item index: temp)
        {
            if(index instanceof Article)
                finalArray.add(index);
        }
        return finalArray;
    }

    public ArrayList<Item> getAllItemsMedia()
    {
        ArrayList<Item> temp = getAllItems();
        ArrayList<Item> finalArray = new ArrayList<Item>();
        for(Item index: temp)
        {
            if(index instanceof Media)
                finalArray.add(index);
        }
        return finalArray;
    }

    public void editItem(Item obj, int index)
    {
        ArrayList<Item> items = getAllItems();
        items.set(index,obj);
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    public void removeItem(Item obj)
    {
        ArrayList<Item> items = getAllItems();
        if(items.contains(obj))
        {
            items.remove(obj);
        }
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    public void saveItem(Item obj)
    {
        ArrayList<Item> items = getAllItems();
        items.add(obj);
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    public void saveItems(ArrayList<Item> obj)
    {
        myFileHandler.writeObjectToFile(objectFileName,obj);
    }



}
