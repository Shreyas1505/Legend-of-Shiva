import java.util.ArrayList;
import java.util.Random;
/**
 *  This is the game class of "Legend Of Shiva" a text based adventure game. 
 *  This is the main class that takes the player to all the loctions in this games,
 *  it lets the player execute commands and ultimately win the game.
 *  
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Shreyas Solanki 
 * @version 2017.12.1
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
    
    //initialising variables of all the rooms.
    private Room westernGhats, templeOfShiva, templeOfSerpents, templeOfMoon, serpentsRoom1, serpentsRoom2; 
    private Room moonRoom1, moonRoom2, shivaRoom1, magicRoom;
    
    //initialising variables of all the items.
    private Item pickaxe, shivaFigurine, goldAmulet, fireTorch, serpentGarland, brokenCompass, ancientFlask;
    private Item inscribedCopperPlate, silverCrescentMoon, illusionDispellingCrystal, goldenCoins, treasureChest, shivaTrident;
    
    //initialising variables of all the characters. 
    private Character protector, serpents, witches, spiritOfShiva;
    
    //declaring ArrayList to store all items.
    private ArrayList<Item> allItems = new ArrayList<Item>();
    
    //setting maximum weight for the inventory.
    private int maxWeight = 50;
    
    //setting initial inventory weight.
    private int inventoryWeight = 0;
    
    //creating an ArrayList for inventory.
    private ArrayList<Item> inventory = new ArrayList<Item>();
    
    private Item itemPicked;                // stores the item that is picked.         
    private int itemPickedWeight;           // stores the weight of the picked item.
    
    //declaring an ArrayList to store all the rooms.
    private ArrayList<Room> allRooms = new ArrayList<>();
    
    private Random roomRandom = new Random(); // declaring random to be applied on rooms.
    
    //declaring an ArrayList to store all the placed items.
    private ArrayList<Item> placedItems = new ArrayList<>();
    
    //declaring an ArrayList to store all the chracters.
    private ArrayList<Character> allCharacters = new ArrayList<>();
    
    //booleans for correctly placed items.
    private boolean itemPlacedCorrectly1 = false;
    private boolean itemPlacedCorrectly2 = false;
    
    //booleans for killed monsters.
    private boolean serpentsKilled = false;
    private boolean witchesKilled = false;
    private boolean predatorKilled = false;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();                 //calling method to create rooms.
        createItems();                 // calling method to create items.
        createCharacters();            // calling method to create characters.
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together. Also this method stores them in an ArrayList.
     */
    private void createRooms()
    {
       // create the rooms
       westernGhats = new Room("in middle of a tropical rainforest in the India");
       templeOfShiva = new Room("in the Temple Of Lord Shiva. Here you can place the artifacts that you collected from the two temples. ");
       templeOfSerpents = new Room("in the Temple Of Serpents. Kill the monster and collect the artifact");
       templeOfMoon = new Room("in the Temple Of Moon. kill the monster and collect the artifact.");
       serpentsRoom1 = new Room("in the weapon room of the temple of serpents.");
       serpentsRoom2 = new Room("in the artifact room of the temple of serpents.");
       moonRoom1 = new Room("in the artifact room of the temple of the moon.");
       moonRoom2 = new Room("in the weapon room of the temple of the moon.");
       shivaRoom1 = new Room("in the underground passage to Shiva's Trident. Defeat the protector using the Trident.");
       magicRoom = new Room("in the teleportation room. Type in magic and see what happens!!");
       
       // initialise room exits
       westernGhats.setExit("TempleOfSerpents", templeOfSerpents);
       westernGhats.setExit("TempleOfShiva", templeOfShiva);
       westernGhats.setExit("TempleOfMoon", templeOfMoon);
       westernGhats.setExit("MagicRoom", magicRoom);

       templeOfSerpents.setExit("Forest", westernGhats);
       templeOfSerpents.setExit("SerpentsWeaponRoom", serpentsRoom1);
       templeOfSerpents.setExit("SerpentsArtifactRoom", serpentsRoom2);

       templeOfMoon.setExit("Forest", westernGhats);
       templeOfMoon.setExit("MoonArtifactRoom", moonRoom1);
       templeOfMoon.setExit("MoonWeaponRoom", moonRoom2);

       templeOfShiva.setExit("Forest", westernGhats);
       templeOfShiva.setExit("ShivaTreasureRoom", shivaRoom1);
        
       serpentsRoom1.setExit("TempleOfSerpents", templeOfSerpents);
        
       serpentsRoom2.setExit("TempleOfSerpents", templeOfSerpents);
       serpentsRoom2.setExit("TempleOfShiva", templeOfShiva);
        
       moonRoom1.setExit("TempleOfMoon", templeOfMoon);
       moonRoom1.setExit("TempleOfShiva", templeOfShiva);
        
       moonRoom2.setExit("TempleOfMoon", templeOfMoon);

       currentRoom = westernGhats;  // start game outside
       
       //add rooms to an arraylist
       allRooms.add(westernGhats);
       allRooms.add(templeOfShiva);
       allRooms.add(templeOfSerpents);
       allRooms.add(templeOfMoon);
       allRooms.add(serpentsRoom1);
       allRooms.add(serpentsRoom2);
       allRooms.add(moonRoom1);
       allRooms.add(moonRoom2);
       
    }
    
    /**
     * Create all the items and link them to their weight and room. Also this method stores them in an ArrayList.
     */
    private void createItems()
    {
        // create the items.
       shivaFigurine = new Item("ShivaFigurine", 70, templeOfSerpents);
       goldAmulet = new Item("GoldAmulet", 30, templeOfSerpents);
       fireTorch = new Item("FireTorch", 50, serpentsRoom1);
       serpentGarland = new Item("SerpentGarland", 50, serpentsRoom2);
       brokenCompass = new Item("BrokenCompass", 10, templeOfMoon);
       ancientFlask = new Item("AnicentFlask", 60, templeOfMoon);
       inscribedCopperPlate = new Item("InscribedCopperPlate", 100, templeOfMoon);
       silverCrescentMoon = new Item("SilverCrescentMoon", 50, moonRoom1);
       illusionDispellingCrystal = new Item("IllusionDispellingCrystal", 10, moonRoom2);
       goldenCoins = new Item("GoldCoins", 5, templeOfShiva);
       shivaTrident = new Item("ShivaTrident", 50, shivaRoom1);
       
       //storing in arraylist.
       allItems.add(shivaFigurine);
       allItems.add(goldAmulet);
       allItems.add(fireTorch);
       allItems.add(serpentGarland);
       allItems.add(brokenCompass);
       allItems.add(ancientFlask);
       allItems.add(inscribedCopperPlate);
       allItems.add(silverCrescentMoon);
       allItems.add(illusionDispellingCrystal);
       allItems.add(goldenCoins);
       allItems.add(shivaTrident);
    }
    
    /**
     * Create all the characters and link to their rooms. This method also stores these characters in an ArrayList.
     */
    private void createCharacters()
    {
        //create characters.
        protector = new Character("Protector", shivaRoom1);
        serpents = new Character("Serpents", serpentsRoom2);
        witches = new Character("Witches", moonRoom1);
        spiritOfShiva = new Character("SpiritOfShiva", allRooms.get(randomGenerator(allRooms.size())));
        
        //storing in arraylist.
        allCharacters.add(protector);
        allCharacters.add(serpents);
        allCharacters.add(witches);
        allCharacters.add(spiritOfShiva);
    }
    
    /**
     * Retrieve name(string) of all the items stored in a room. 
     */
    private String itemList(Room cRoom)
    {
        String list = "";              //creating a string to store the names of the items.
        
        //looping through all the items in ArrayList.
        for(int i = 0; i < allItems.size(); i++){
           //relating items to rooms.
            if(allItems.get(i).getRoom() == cRoom){
            list = list + " |" + allItems.get(i).getName() + "| ";  //getting item names.
           } 
        }
        return list;
    }
    
    /**
     * Retrieve name(string) of all the characters stored in a room.
     */
    private String characterList(Room cRoom)
    {
        String list = "";            //creating a string to store the names of the characters.
        
        //looping through all the characters in ArrayList.
        for(int i = 0; i < allCharacters.size(); i++){
            //relating characters to rooms.
            if(allCharacters.get(i).getCharRoom() == cRoom){
                list = list + " |" + allCharacters.get(i).getCharName() + "| ";   //getting character names.
            }
        }
        return list;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Legend Of Shiva.");
        System.out.println("Legend Of Shiva is a new adventure game, You play as Norman Drake a treasure hunter.");
        System.out.println("You are searching for the Legendary Trident of the Hindu God Shiva.");
        System.out.println("Navigate through the temples, collect the artifacts ");
        System.out.println("and place them in the right spots to unlock the secret passage to Shiva's Trident");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println("Items:" + itemList(currentRoom));
        System.out.println("Characters:" + characterList(currentRoom));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("pick")) {
            pickItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("place")) {
            placeItem(command);
        }
        else if (currentRoom==magicRoom && commandWord.equals("magic")){
            teleportMagic(command);
        }
        else if (commandWord.equals("kill")) {
            killMonster(command);
        }
        else if (commandWord.equals("inventory")) {
            for(int i = 0; i < inventory.size(); i++){
                System.out.println("Inventory: " + inventory.get(i).getName());
            }
        }
        else if (commandWord.equals("escape")) {
            escapeForest(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Go to the the Temple of Serpents and Temple of Moon");
        System.out.println("Pick the weapons from the weapon rooms in both the temples.");
        System.out.println("Kill the monster in the artifact room using these weapons.");
        System.out.println("Now pick the artifacts and take them to the Temple of Shiva ");     
        System.out.println("Place these artifacts in the right order to access the secret passage to Siva's Trident."); 
        System.out.println("(Use the place command to place the items.)");
        System.out.println("Place the Serpent garland down(Command: place SerpentGarland down).");
        System.out.println("Place the Silver Crescent Moon down(Command: place SilverCrescentMoon up).");
        System.out.println("Finally kill the protector using the Trident and escape(Command: escape) the Forest.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        lastRoom = currentRoom;           //stores currentRoom in a variable to be used in back method.
        Room nextRoom = currentRoom.getExit(direction);
        
        //conditions so that player cannot enter the secret room without placing the right items in the right positions.
        if(itemPlacedCorrectly1==false && itemPlacedCorrectly2==false && direction.equals("down")) {
           System.out.println("You cannot enter this room.");
           return;
        }
        else if(itemPlacedCorrectly1==false && itemPlacedCorrectly2==true && direction.equals("down")){
           System.out.println("You cannot enter this room.");
           return;
        }
        else if(itemPlacedCorrectly1==true && itemPlacedCorrectly2==false && direction.equals("down")){
           System.out.println("You cannot enter this room.");   
           return;
        }        
        else if(itemPlacedCorrectly1==true && itemPlacedCorrectly2==true && direction.equals("down")){
            currentRoom = shivaRoom1;
            System.out.println(currentRoom.getLongDescription());
        }
        
        //takes you to the nextRoom.
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("Items:" + itemList(currentRoom));
            System.out.println("Characters:" + characterList(currentRoom));
        }
    }
    
    /**
     * Try to pick the items in a room. When the item is picked it is removed from the room.
     */
    private void pickItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick...
            System.out.println("Pick what?");
            return;
        }
        
        //get the weight of the picked items.
        String itemName = command.getSecondWord();
        for(int i = 0; i < allItems.size(); i++){
            if(allItems.get(i).getName().equalsIgnoreCase(itemName) && !allItems.get(i).picked()){
                itemPicked = allItems.get(i);
                itemPickedWeight = itemPicked.getWeight();
            }
        }
        // Try to pick item.
        
        //condition for picking the artifacts from the artifact rooms.
        if(currentRoom == serpentsRoom2 && serpentsKilled == true && itemName.equals("serpentGarland")){
            
        }
        else if(currentRoom == moonRoom1 && witchesKilled == true && itemName.equals("silverCrescentMoon")){
            
        }
        else if (currentRoom == serpentsRoom2 || currentRoom == moonRoom1){ 
            System.out.println("Kill the creature first!");
            return;
        }
        
        //picks items which do not exceed the weight limit
        if( inventoryWeight + itemPickedWeight > maxWeight){
            System.out.println("This item weight is too much!");
        } else {
            itemPicked.togglePicked();
            inventory.add(itemPicked);
            System.out.println(itemPicked.getName() + " has been added to your inventory!");
            inventoryWeight += itemPickedWeight;
            System.out.println("Inventory Weight: " + inventoryWeight);
            
        }
    }
    
    /**
     * Drops the items that have boon pick earlier and removes them from the inventory.
     */
    private void dropItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        // Try to drop item.
        
        int currentWeight = inventoryWeight - itemPickedWeight;
        itemPicked.togglePicked();
        inventory.remove(itemPicked);
        inventoryWeight = currentWeight;
        System.out.println("Inventory Weight: " + inventoryWeight);
    }
    
    /**
     * Places items in a particular spots. The serpent graland and the silver crecent moon need to placed in particular spots to open 
     * the secret passage to the trident.
     */
    private void placeItem(Command command)
    {
     if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to place...
            System.out.println("Place what?");
            return;
     }
        
     if(!command.hasThirdWord()) {
            // if there is no second word, we don't know what to place...
            System.out.println("Place where?");
            return;
     }
        
     String itemName = command.getSecondWord();
     String itemLocation  = command.getThirdWord();
        
     //condition to place particular items in particular spots.  
     
     if(currentRoom==templeOfShiva && itemName.equals("serpentGarland") && inventory.contains(serpentGarland) && itemLocation.equals("down")){
            placedItems.add(serpentGarland);
            inventory.remove(serpentGarland);
            inventoryWeight -= serpentGarland.getWeight();
            System.out.println("SerpentGarland has been placed successfully!!");
            System.out.println(currentRoom.getLongDescription());
            System.out.println("Items:" + itemList(currentRoom));
            System.out.println("Characters:" + characterList(currentRoom));
            itemPlacedCorrectly1 = true;
     }else if(currentRoom==templeOfShiva && itemName.equals("silverCrescentMoon") && inventory.contains(silverCrescentMoon) && itemLocation.equals("up")){
            placedItems.add(silverCrescentMoon);
            inventory.remove(silverCrescentMoon);
            inventoryWeight -= silverCrescentMoon.getWeight();
            System.out.println("silverCrescentMoon has been placed successfully!!");
            System.out.println(currentRoom.getLongDescription());
            System.out.println("Items:" + itemList(currentRoom));
            System.out.println("Characters:" + characterList(currentRoom));
            itemPlacedCorrectly2 = true;
        }else {
            System.out.println("This Item cannot be placed!!");
            System.out.println("Try placing a different item ");
            System.out.println("or try placing the item at a different spot.");
        }
    }
    
    /**
     * Kills the monsters that appear in front of the player.
     */
    private void killMonster(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to kill...
            System.out.println("Kill what?");
            return;
        }
        
        String charName = command.getSecondWord();
        
        //try to kill the three monsters.
        if(currentRoom==serpentsRoom2 && inventory.contains(fireTorch) && charName.equalsIgnoreCase("Serpents")){
            allCharacters.remove(serpents);
            inventory.remove(fireTorch);
            System.out.println("The serpents were killed! You can pick the artifact now!");
            serpentsKilled = true;
        }else if(currentRoom==moonRoom1 && inventory.contains(illusionDispellingCrystal) && charName.equalsIgnoreCase("Witches")){
            allCharacters.remove(witches);
            inventory.remove(illusionDispellingCrystal);
            System.out.println("The witches were killed! You can pick the artifact now!");
            witchesKilled = true;
        }else if(currentRoom==shivaRoom1 && inventory.contains(shivaTrident) && charName.equals("Protector")){
            allCharacters.remove(protector);
            System.out.println("The Protector was killed! Escape the forest with the trident!");
        }
        else if(!inventory.contains(fireTorch) || !inventory.contains(illusionDispellingCrystal) || !inventory.contains(shivaTrident)){
            System.out.println("You cannot kill this monster.");
        }
    }
    
    /**
     * Lets to talk to the randomly appearing character called Spirit Of Shiva.
     */
    private void talkToSpirit(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to talk...
            System.out.println("Talk what?");
            return;
        }
        
        if(!command.hasThirdWord()) {
            // if there is no second word, we don't know what to talk...
            System.out.println("Talk to what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        String itemLocation  = command.getThirdWord();
        
        //try to talk to Spirit of shiva
        
        if(command.getSecondWord().equalsIgnoreCase("to") && command.getThirdWord().equalsIgnoreCase("SpiritOfShiva")) {
            System.out.println("Two things define you. Your patience when you have nothing ");
            System.out.println("and your attitude when you have everything.");
        }
    }
    
    /**
     * Takes you back to the previous room.
     */
    private void back(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("Back what?");
            return;
        }
        else{
            currentRoom = lastRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("Items:" + itemList(currentRoom));
            System.out.println("Characters:" + characterList(currentRoom));
        }
    }
    
    /**
     * Generates a random number to be used in teleportMagic command.
     */
    private int randomGenerator(int size)
    {
        return roomRandom.nextInt(size);
    }
    
    /**
     * Gives a random room. Used in the magicRoom where typing magic takes you to a random room.
     */
    private void teleportMagic(Command command)
    {
        currentRoom = allRooms.get(randomGenerator(allRooms.size()));
        System.out.println(currentRoom.getLongDescription());
        System.out.println("Items: " + itemList(currentRoom));
        System.out.println("Characters:" + characterList(currentRoom));
    }
    
    /**
     * When winning conditions are satisfied you can leave the forest. 
     */
    private void escapeForest(Command command)
    {
        if(inventory.contains(shivaTrident)){
            System.out.println("You have successfully escaped the forest with the legendary Trident of Shiva!");
            System.out.println("CONGRATULATIONS!! You have won the game!");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
