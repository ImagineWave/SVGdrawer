package storages;

import abstracts.Item;
import abstracts.Shape;
import abstracts.Storage;
import exceptions.*;
import utils.SVGWriter;

import java.io.IOException;
import java.util.ArrayList;

public class Bag extends Storage {
    public static final double maxWeight = 50;
    public static final int drawInterval = 10;
    public Bag (String name, String color, Shape shape, double weight, int size){
        super(
                name, color, shape, weight, size
        );
    }

    @Override
    public void put(Item item) {
        ArrayList<Item> content = getContent();
        if(canPut(item)) {
            content.add(item);
            setContent(content);
            item.setInStorage(true);
        } else {
            throw new CantPutItemException("cant put item it storage (unknown reason)");
        }
    }
    @Override
    public double getWeight(){
        double contentWeight = super.getTotalWeight();
        double defaultBagWeight = this.getWeightOfEmptyStorage();
        return  contentWeight+defaultBagWeight;
    }
    public static Bag createBag(String name, String color, int size){
        Bag bag = new Bag(name, color, Shape.SPHERE, 1,size);

        return bag;
    }
    public static Bag createDefaultBag(){
        Bag bag = new Bag("bag", "burlywood", Shape.SPHERE, 1,5);

        return bag;
    }
    /**
     * Конкретный индекс
     **/
    public void remove(int index){
        ArrayList<Item> content = getContent();
        if(index<content.size()){
            content.remove(index);
        }
        else throw new IndexOutOfBoundsException("index is higher than size");
    }

    /**
     *
     * @param name
     * @return
     */
    public Item getItemByName(String name){
        ArrayList<Item> content = getContent();
        for(Item item : content){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        throw new ItemNotFoundException("there is no \""+name+"\" in this storage");
    }
    /**
     * Первый попавшийся с таким цвеом
    **/
    public Item getItemByColor(String color){
        ArrayList<Item> content = getContent();
        for(Item item : content){
            if(item.getColor().equalsIgnoreCase(color)){
                return item;
            }
        }
        throw new ItemNotFoundException("there is no items with color \""+color+"\" in this storage");
    }
    public Item getRandomItem(){
        int amount = getContent().size();
        double d= Math.random()*amount+0.1d;
        int index = (int)d;
        return getItem(index);
    }

    @Override
    public double getCapacity(){
        return Bag.maxWeight;
    }
    @Override
    protected void checkSize(){

    }
}
