import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> items;

    public ContainerItem(String name, String type, String description){
        super(name, type, description);
        items = new ArrayList<Item>();
    }

    public void addItem(Item itemSelected){
        items.add(itemSelected);
    }

    public boolean hasItem(String itemName){
        return items.stream()
                    .anyMatch(item -> item.getName().equalsIgnoreCase(itemName));    
    }

    public Item removeItem(String itemName){
        Item found = hasItem(itemName) 
            ? items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .get() 
            : null;
        items.remove(found);
        return found;
    }

    public String toString(){
        String stringContainerItem =  getName() + " [ " + getType() + " ] : " + getDescription();
        if(items.isEmpty()){
            stringContainerItem += "\n + No items yet";
        }
        for(int i = 0; i < items.size(); i++){
            stringContainerItem += "\n + " + items.get(i).getName();
        }
        return stringContainerItem;
    }
}
