import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private HashMap<String, Location> connections;

    public Location(String name, String description){
        this.name = name;
        this.description = description;
        this.items = new ArrayList<Item>();
        this.connections = new HashMap<String,Location>();
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public boolean hasItem(String itemName){
        return items.stream()
            .anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public Item getItem(String itemName){
        Item itemFound =  hasItem(itemName) 
            ? items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .get() 
            : null;
        return itemFound;
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public int numItems(){
        return items.size();
    }

    public Item removeItem(String itemName){
        Item itemFound = getItem(itemName); 
        if (itemFound != null){
            items.remove(itemFound);
        }
        return itemFound;
    }

    public void connect(String direction, Location location){
        connections.put(direction, location);
    }

    public boolean canMove(String direction){
        if(connections.containsKey(direction)){
            return true;
        }
        else{
            return false;
        }
    }

    public Location getLocation(String direction){
        if (canMove(direction)){
            return connections.get(direction);
        }
        return null;
    }
}

