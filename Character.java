
/**
 * Class Character - a character in an adventure game.
 * 
 * This class is part of the "Legend Of Shiva" application. 
 * "Legend Of Shiva" is a text based adventure game. 
 * 
 * A character is  stored in a room similar to an item but some 
 * characters can move, some can protect items. Characters can also be killed.
 * 
 * @author  Shreyas Solanki 
 * @version 2017.12.1
 */
public class Character
{
    private String name;
    private Room room;
    
    /**
     * Creates a name and Room for this character 
     */
    public Character(String charName, Room charRoom)
    {
        name = charName;
        room = charRoom;
    }
    
    /**
     * returns this character's name.
     */
    public String getCharName(){
        return name;
    }
    
    /**
     * returns this character's room.
     */
    public Room getCharRoom(){
        return room;
    }
    
}
