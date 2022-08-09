package storages;

import abstracts.Item;
import abstracts.Shape;
import abstracts.Storage;
import exceptions.ItemNotFoundException;
import exceptions.CantPutItemException;
import utils.SVGWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

public class Box extends Storage {
    public static final double maxWeight = 50;
    public static final int drawInterval = 10;
    public Box (String name, String color, Shape shape, double weight, int size){
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
    public static Box createBox(String name, String color){
        Box box = new Box(name, color, Shape.CUBE, 1,10);

        return box;
    }
    public static Box createDefaultBox(){
        Box box = new Box("box", "burlywood", Shape.CUBE, 1,10);

        return box;
    }
    //Первый попавшийся с таким именем
    public Item getByName(String name){
        ArrayList<Item> content = getContent();
        for(Item item : content){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        throw new ItemNotFoundException("there is no \""+name+"\" in this storage");
    }
    //Первый попавшийся с таким цвеом
    public Item getByColor(String color){
        ArrayList<Item> content = getContent();
        for(Item item : content){
            if(item.getColor().equalsIgnoreCase(color)){
                return item;
            }
        }
        throw new ItemNotFoundException("there is no items with color \""+color+"\" in this storage");
    }
    public Item getRandom(){
        int amount = getContent().size();
        //ThreadLocalRandom.current().nextDouble(0, amount);
        double d= ThreadLocalRandom.current().nextDouble(0, amount);
        int index = (int)d;
        return getItem(index);
    }
    public double getCapacity(){
        return Box.maxWeight;
    }

    @Override
    protected void checkSize(){

    }
    @Override
    public void draw(SVGWriter writer, int x, int y) throws IOException {
        writer.writeRoundRect(x,y, this.getWidth(),this.getHeight(), this.getColor());
        int boxWidth = this.getWidth();
        int boxHeight = this.getHeight();
        int curentX = x+drawInterval;
        int curentY = y+drawInterval;
        for(Item item: getContent()){
            if(curentX+item.getWidth()<= x+boxWidth){
                item.draw(writer, curentX, curentY);
                curentX+= item.getWidth()+drawInterval;
            }

            //item.draw(writer, curentX, curentY);
            //for(; curentX<=boxWidth; curentX+= item.getWidth()+10){
            //}
        }

    }
}
