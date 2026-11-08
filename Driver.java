
import java.util.Scanner;

public class Driver{

    public static Location currLocation;
    public static ContainerItem myInventory;

    public static void main(String[] args){
        createWorld();

        Scanner input = new Scanner(System.in);
            while (true){
                System.out.print("Enter Command: ");
                String command = input.nextLine();
                String[] words = command.split(" ");
                switch (words[0].toLowerCase()){
                    case "quit":
                        {
                            input.close();
                            return;
                        }
                    case "look":
                        {
                            System.out.println(currLocation.getName() + " - " + currLocation.getDescription() + " currently has the following items: ");
                            for(int i = 0; i < currLocation.numItems(); i++){
                                System.out.println(" + " + currLocation.getItem(i).getName());
                            }
                            break;
                        }
                    case "examine":
                        {
                            if (words.length == 1){
                                System.out.println("Examine what?");
                                break;
                            }
                            boolean found = false;
                            if(currLocation.hasItem(words[1])){
                                found = true;
                            }
                            if (found == true){
                                System.out.println(currLocation.getItem(words[1]));
                            }
                            if (found == false){
                                System.out.println("Cannot find that Item");
                            }
                            break;
                        }
                    case "go":
                    {
                        if (words.length == 1){
                            System.out.println("Go where?");
                            break;
                        }
                        String direction = words[1].toLowerCase();

                        if(direction.equals("north") || direction.equals("south") ||
                            direction.equals("east") || direction.equals("west")){

                            if(currLocation.canMove(direction)){
                                currLocation = currLocation.getLocation(direction);
                                System.out.println(currLocation.getName());
                            }
                            else{
                                System.out.println("Cannot go that way");
                            }
                            }
                        else{
                            System.out.println("Invalid direction");
                        }
                        break;
                    }
                    case "inventory":
                    {
                        System.out.println(myInventory.toString());
                        break;
                    }
                    case "take":
                    {
                        if (words.length == 1){
                                System.out.println("Take what?");
                                break;
                            }
                        else if(currLocation.hasItem(words[1])){
                            myInventory.addItem(currLocation.removeItem(words[1]));
                            System.out.println("You have taken the " + words[1]);
                        }
                        else{
                            System.out.println("Cannot find that item here");
                        }
                        break;
                    }
                    case "drop":
                    {
                        if (words.length == 1){
                                System.out.println("Drop what?");
                                break;
                        }
                        else if(myInventory.hasItem(words[1])){
                            currLocation.addItem(myInventory.removeItem(words[1]));
                            System.out.println("You have dropped the " + words[1]);
                        }
                        else{
                            System.out.println("Cannot find that item in you inventory");
                        }
                        break;
                    }
                    case "help":
                    {
                        helper();
                        break;
                    }
                    default:
                    {
                        System.out.println("I don't know how to do that");
                        break;
                    }
                }
        }
    }

    public static void createWorld(){
        Location Pharmacy = new Location("Pharmacy" ,  "A small pharmacy...painted in white...with different kinds of medicine");
        Location Laboratory = new Location("Laboratory", "A huge Laboratory...painted in green...with different type of animals");
        Location Hallway = new Location("Hallway", "A long hallway with white walls....");
        Location Shelter = new Location("Shelter", "A small shelter with some products to eat and drink....");
        ContainerItem BackPack = new ContainerItem("BackPack", "Tool", "A large hiking backpack....that contains: ");

        Pharmacy.connect("south", Hallway);
        Hallway.connect("north", Pharmacy);

        Hallway.connect("east", Laboratory);
        Laboratory.connect("west", Hallway);

        Laboratory.connect("north", Shelter);
        Shelter.connect("south", Laboratory);
        
        Item Vaccine = new Item("Vaccine", "Medicine", "A vaccine... Might vaccinate you from a unknown virus");
        Item Coat = new Item("Coat", "Clothing", "A white doctor's coat....Might be useful for keeping your warm");
        Item Bandage = new Item("Bandage", "Tool", "A pack of bandage... Might be useful for covering wounds ");
        Item Key = new Item("Key", "Tool", "A small silver key");
        Item Flashlight = new Item("Flashlight", "Tool", "An old flashlight... Half percentage charged");
        Item Cookie = new Item("Cookie", "Food", "A chocolate chip cookie... Might be really delicious");
        Item Serum = new Item("Serum", "Liquid", "A strange green liquid... Store in the glass sealer");
        Item Mouse = new Item("Mouse", "Animal", "A dead mouse... Eye color is green");

        Pharmacy.addItem(Vaccine);
        Pharmacy.addItem(Coat);
        Pharmacy.addItem(Bandage);
        Hallway.addItem(Key);
        Shelter.addItem(Flashlight);
        Shelter.addItem(Cookie);
        Laboratory.addItem(Serum);
        Laboratory.addItem(Mouse);

        currLocation = Pharmacy;
        myInventory = BackPack;
    }
    
    public static void helper(){
        String[] helpCommand = {"quit: quit the game",
                                "look: look for item in current location",
                                "examine: examine if the item exists",
                                "go: go to direction you want",
                                "inventory: check your inventory", 
                                "take: take the item and put it in your inventory",
                                "drop: drop the item from your inventory"};
        for(int i = 0; i < helpCommand.length; i++){
            System.out.println(helpCommand[i]);
        }

    }

}
