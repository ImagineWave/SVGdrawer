package abstracts;

import exceptions.ItemNotFoundException;
import exceptions.SameItemException;
import exceptions.StorageLimitException;
import exceptions.ItemAlreadyStoredException;

import java.util.ArrayList;

public abstract class Storage extends Item {
    public Storage(String name, String color, Shape shape, double weight, int size) {
        super(
                name, color, shape, weight, size
        );
    }

    public ArrayList<Item> getContent() {
        return content;
    }

    protected void setContent(ArrayList<Item> content) {
        this.content = content;
    }

    private ArrayList<Item> content = new ArrayList<>();
    public double getTotalWeight() {
        double output = 0d;
        for (Item item : content) {
            output = output + item.getWeight();
        }
        return output;
    }
    protected double getWeightOfEmptyStorage(){
        return super.getWeight();
    }
    @Override
    public double getWeight(){
        double contentWeight = getTotalWeight();
        double defaultBagWeight = super.getWeight();
        return  contentWeight+defaultBagWeight;
    }
    public abstract double getCapacity();
    @Override
    public String toString(){
    	String result = "";
    	result+="Name: "+this.getName()+"\n";
    	result+="Color: "+this.getColor()+"\n";
    	result+="Shape: "+this.getShape().toString()+"\n";
    	double d = this.getWeight();

    	result+="Weight: "+d+"\n";
    	Integer s = this.getSize();
    	result+="Size: "+s.toString()+"\n";
    	Boolean b = this.isInStorage();
    	result+="Stored: "+b.toString()+"\n";
    	return result;
    }
    public abstract void put(Item item);
    public Item getItem(int index){
        return content.get(index);
    }
    //Берет по имени
    //Показывает сожержимое
    public void showContent(){
        System.out.println(this.getName()+"`s content:");
        for(int i = 0; i<content.size(); i++){
            System.out.println( i+": ---------");
            System.out.println(content.get(i).toString());
        }
    }
    public Item remove(Item item){
        item.setInStorage(false);
        if(!content.contains(item)){
            throw new ItemNotFoundException ("storage does not contain this item");
        }
        content.remove(item);
        return item;
    }
    private boolean isTooHeavy(Item item){
        if(item instanceof Storage){
            if( ((Storage) item).getTotalWeight()> getCapacity()){
                throw new StorageLimitException("new weight: "+((Storage) item).getTotalWeight()+" max allowed weight: "+getCapacity());
            }
        }
        if(getTotalWeight()+item.getWeight()>getCapacity()){
            throw new StorageLimitException ("new weight: "+(getTotalWeight()+item.getWeight())+" max allowed weight: "+getCapacity());
        }
        return false;
    }
    protected boolean isTheSameItem(Item item) {
        if (this.equals(item)) {
            throw new SameItemException("u cant put things into itself");
        }
        return (this.equals(item));
    }
    protected boolean itemIsStored(Item item){
        if(item.isInStorage()){
            throw new ItemAlreadyStoredException("this item already stored");
        }
        return item.isInStorage();
    }

    public boolean canPut(Item item){
        if(isTooHeavy(item)){
            return false;
        }
        if(isTheSameItem(item)){
            return false;
        }
        if(itemIsStored(item)){
            return false;
        }
        return true;
    }
}
