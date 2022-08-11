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
    public static final double maxWeight = 500;
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
    public static Box createBox(String name, String color, int  size){
        Box box = new Box(name, color, Shape.CUBE, 1,size);

        return box;
    }
    public static Box createDefaultBox(){
        Box box = new Box("box", "burlywood", Shape.CUBE, 1,20);

        return box;
    }
    //Первый попавшийся с таким именем
    public Item getItemByName(String name){
        ArrayList<Item> content = getContent();
        for(Item item : content){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        throw new ItemNotFoundException("there is no \""+name+"\" in this storage");
    }
    //Первый попавшийся с таким цвеом
    public Item getItemByColor(String color){
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
        writer.writeRect(x,y, this.getWidth(),this.getHeight(), this.getColor());
        int boxWidth = this.getWidth();
        int boxHeight = this.getHeight();
        int boxXedge = boxWidth+x;
        int boxYedge = boxHeight+y;
        int curentX = x+drawInterval;
        int curentY = y+drawInterval;
        int maxY = 0;
        ArrayList<Item> badItems = new ArrayList<>();

        //НОРМАЛЬНЫЕ ПРЕДМЕТЫ
        for(Item item: getContent()){
            if(item.getShape().equals(Shape.SPHERE)){
                badItems.add(item);
                continue;
            }
            if(curentX+item.getWidth()<= x+boxWidth){
                item.draw(writer, curentX, curentY);
                curentX+= item.getWidth()+drawInterval;
                if(item.getHeight()>maxY) maxY=item.getHeight();
            } else if(curentY+item.getWidth()<=boxYedge){
                curentX= x+drawInterval;
                curentY+= maxY+drawInterval;
                maxY=0;
                item.draw(writer, curentX, curentY);
            }
        }

        for(Item item:badItems){
            if(curentX+item.getWidth()< x+boxWidth){
                item.draw(writer, curentX, curentY);
                curentX+= item.getWidth()+drawInterval;
                if(item.getHeight()>maxY) maxY=item.getHeight();
            } else if(curentY+item.getHeight()*2<boxYedge){
                //item.draw(writer, curentX-item.getWidth()/2, curentY-item.getHeight()/2);

                //item.draw(writer, curentX, curentY);
                curentX= x+drawInterval;
                curentY+= maxY+drawInterval;
                maxY=0;
                item.draw(writer, curentX, curentY);

            }
        }
    }
}
