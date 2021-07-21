package Utils;

import Model.Article;
import Model.Book;
import Model.Item;
import Model.Media;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A class that include all the function to read and write from the txt and bin file
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class FairyTaleModelManager {
    private String objectFileName;
    private String textFileName;
    private MyFileHandler myFileHandler;

    /**
     * A 2-argument constructor
     * @param objectFileName the binary filename
     * @param textFileName the text filename
     */
    public FairyTaleModelManager(String objectFileName, String textFileName)
    {
        this.objectFileName=objectFileName;
        this.textFileName=textFileName;
        this.myFileHandler=new MyFileHandler();
    }

    /**
     * A method that will return from binary file a list of Items
     * @return a list of items
     */
    public ArrayList<Item> getAllItems()
    {
        ArrayList<Item> items = (ArrayList<Item>) myFileHandler.readObjectFromFile(objectFileName);
        return items;
    }

    /**
     * A method that will return from binary file a list of items base on the title
     * @param title the title that will be used for search
     * @return a list of items base on the specific title
     */
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

    /**
     * A get method that will return all the book items
     * @return a list of book items
     */
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

    /**
     * A get method that will return all the article items
     * @return a list of article items
     */
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

    /**
     * A get method that will return all the media items
     * @return a list of media items
     */
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

    /**
     * A method that will edit an Item and save into the binary file
     * @param obj the new item
     * @param index the index in the list
     */
    public void editItem(Item obj, int index)
    {
        ArrayList<Item> items = getAllItems();
        items.set(index,obj);
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    /**
     * A method that will remove an Item and save into binary file
     * @param obj the specific item
     */
    public void removeItem(Item obj)
    {
        ArrayList<Item> items = getAllItems();
        if(items.contains(obj))
        {
            items.remove(obj);
        }
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    /**
     * A method that will save an object to binary file
     * @param obj the item
     */
    public void saveItem(Item obj)
    {
        ArrayList<Item> items = getAllItems();
        items.add(obj);
        myFileHandler.writeObjectToFile(objectFileName,items);
    }

    /**
     * A method that will save the list of items to binary file
     * @param obj the list of items
     */
    public void saveItems(ArrayList<Item> obj)
    {
        myFileHandler.writeObjectToFile(objectFileName,obj);
    }

    /**
     * A method that will read data from txt file and will save in binary file
     * @throws FileNotFoundException an exception will be trow if he can see the file
     */
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
