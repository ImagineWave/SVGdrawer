package storages;

import abstracts.Item;
import abstracts.Shape;
import abstracts.Storage;
import exceptions.CantPutItemException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Stack extends Storage {
    public static final int maxAmount = 5;

    private static final int drawInterval = 0;

    public Stack (String name, String color, Shape shape, double weight, int size){
        super(
                name, color, shape, weight, size
        );
    }

    public static Stack createStack(){
        return new Stack("Stack", null, Shape.FLAT, 0d, 0);
    }
    @Override
    public double getCapacity() {
        return Stack.maxAmount;
    }

    @Override
    public void put(Item item) {
        ArrayList<Item> content = getContent();
        if (canPut(item)) {
            content.add(item);
            setContent(content);
            item.setInStorage(true);
        } else {
            throw new CantPutItemException("cant put item it storage (unknown reason)");
        }
    }
    public void remove(){
        ArrayList<Item> content = getContent();
        content.remove(content.size()-1);
    }

    @Override
    public boolean canPut(Item item){
        if(!allowedShape(item)){
            return false;
        }
        if(!canPutMore()){
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
    private boolean allowedShape(Item item){
        if(item.getShape().equals(Shape.CUBE)) return true;
        if(item.getShape().equals(Shape.FLAT)) return true;
        return false;
    }
    private boolean canPutMore() {
        return (getContent().size()<Stack.maxAmount);
    }
    @Override
    protected void checkSize(){

    }
}
