
/**
 * Class Item - an item in an adventure game.
 * 
 * This class is part of the "Legend Of Shiva" application. 
 * "Legend Of Shiva" is a text based adventure game. 
 *
 * An item represents an object stored in a room. The player can
 * pick the items, drop the items, place the items and also kill 
 * monsters using some items.
 *
 * @author  Shreyas Solanki 
 * @version 2017.12.1
 */
public class Item
{
    private String name;
    private int weight;
    private Room room;
    private boolean picked;
    
    /**
     * Create an item name, item weight and item room.
     */
    public Item(String itemName, int itemWeight,Room itemRoom){
        name = itemName;
        weight = itemWeight;
        room = itemRoom;
        picked = false;
    }
    
    /**
     * return the name of this item.
     */
    public String getName(){
        return name;
    }
    
    /**
     * return weight of this item.
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * return room of this item.
     */
    public Room getRoom(){
        return room;
    }
    
    /**
     * return boolean of this item. 
     */
    public boolean picked(){
        return picked;
    }
    
    /**
     * Toggles the boolean. True if the item is picked and false when it is not picked.
     */
    public void togglePicked(){
        if(picked == true){
            picked = false;
        } else {
            picked = true;
        }
    }
}
