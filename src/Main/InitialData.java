package Main;

import Model.*;
import Utils.FairyTaleModelManager;

public class InitialData {

    public static void main(String[] args)
    {
        Library library = new Library();
        FairyTaleModelManager manager = new FairyTaleModelManager("items.bin","items.txt");

        Item item1 = new Book("Morometii","Ion","12345");
        Item item1a = new Book("Morometii2","Ion","163e142");
        Item item1b = new Book("Tet","Ion","163611");
        Item item1c = new Book("Testing","Adrian","1251");
        Item item2 = new Article("Google", "Google.Inc","Bilka");
        Item item3 = new Media("COD","Activision","CD",false);
        Item item4 = new Media("COD2","Activision","DVD",true);

        library.addItem(item1);
        library.addItem(item1a);
        library.addItem(item1b);
        library.addItem(item1c);
        library.addItem(item2);
        library.addItem(item3);
        library.addItem(item4);

        manager.saveItems(library.getItems());
        System.out.println("File has been saved");
    }

}
