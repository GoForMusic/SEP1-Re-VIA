package Utils;

import Model.Article;
import Model.Book;
import Model.Item;
import Model.Media;

import java.io.FileNotFoundException;
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

    public void saveDataFromTxt() throws FileNotFoundException {
        ArrayList<String> stringList = myFileHandler.readFromTextFile(textFileName);

        ArrayList<Item> finalList = getAllItems();

        for(String element:stringList)
        {
            String itemType = element.split("\\(")[0];
            String itemTitle="";
            String itemAuthor="";
            switch (itemType)
            {
                case "Book":
                    itemTitle = element.split("\\,")[0];
                    itemTitle = itemTitle.substring(itemTitle.indexOf("Title:")+6);
                    itemAuthor = element.split("\\,")[1];
                    itemAuthor = itemAuthor.substring(itemAuthor.indexOf("Author:") +7);
                    String ISBN = element.split("\\)")[0];
                    ISBN = ISBN.substring(ISBN.indexOf("ISBN:")+5);
                    finalList.add(new Book(itemTitle,itemAuthor,ISBN));
                    continue;
                case "Article":
                    itemTitle = element.split("\\,")[0];
                    itemTitle = itemTitle.substring(itemTitle.indexOf("Title:")+6);
                    itemAuthor = element.split("\\,")[1];
                    itemAuthor = itemAuthor.substring(itemAuthor.indexOf("Author:") +7);
                    String magazine = element.split("\\)")[0];
                    magazine = magazine.substring(magazine.indexOf("Magazine:")+9);
                    finalList.add(new Article(itemTitle,itemAuthor,magazine));
                    continue;
                case "Media":
                    itemTitle = element.split("\\,")[0];
                    itemTitle = itemTitle.substring(itemTitle.indexOf("Title:")+6);
                    itemAuthor = element.split("\\,")[1];
                    itemAuthor = itemAuthor.substring(itemAuthor.indexOf("Author:") +7);
                    String type = element.split("\\,")[2];
                    type = type.substring(type.indexOf("type:")+5);
                    String newItem = element.split("\\)")[0];
                    newItem = newItem.substring(newItem.indexOf("newMedia:")+9);
                    finalList.add(new Media(itemTitle,itemAuthor,type,newItem.equals("Yes")?true:false));
                    continue;
            }
        }
        saveItems(finalList);
    }

}
