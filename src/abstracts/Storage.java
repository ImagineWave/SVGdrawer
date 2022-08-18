package abstracts;

import exceptions.ItemNotFoundException;
import exceptions.SameItemException;
import exceptions.StorageLimitException;
import exceptions.ItemAlreadyStoredException;
import utils.SVGWriter;

import java.io.IOException;
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
    private static final int drawInterval = 10;
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
    @Override
    public void draw(SVGWriter writer, int x, int y) throws IOException {
        writer.writeRect(x,y, this.getWidth(),this.getHeight(), this.getColor());
        int boxWidth = this.getWidth();
        int boxHeight = this.getHeight();
        int boxYedge = boxHeight+y;
        int curentX = x+drawInterval;
        int curentY = y+drawInterval;
        int maxY = 0;
        ArrayList<Item> badItems = new ArrayList<>();

        //НОРМАЛЬНЫЕ ПРЕДМЕТЫ
        for(Item item: getContent()){
            boolean isDrawed = false;
            if(item.getShape().equals(Shape.SPHERE)){
                badItems.add(item);
                continue;
            }
            while (!isDrawed){
                if(curentX+item.getWidth()<= x+boxWidth){
                    item.draw(writer, curentX, curentY);
                    isDrawed = true;
                    curentX+= item.getWidth()+drawInterval;
                    if(item.getHeight()>maxY) maxY=item.getHeight();
                }else if(curentY+item.getWidth()<=boxYedge){
                    curentX= x+drawInterval;
                    curentY+= maxY+drawInterval;
                    maxY=0;
                    //item.draw(writer, curentX, curentY);
                    //isDrawed = false;
                }
            }

        }
    }
    public void draw2(SVGWriter writer, int x, int y) throws IOException{
        writer.writeRect(x,y, this.getWidth(),this.getHeight(), this.getColor());
        int boxX = x;
        int boxMaxX = x+this.getWidth();
        int boxMayY = y+this.getHeight();
        int maxHeight = 0;
        x= x+drawInterval;
        y= y+drawInterval;
        ArrayList<Item> badItems = new ArrayList<>();
        for(Item item: this.getContent()){
            /**
             * Сортировка вещей. СНАЧАЛА плоские, потом НЕ плоские
             */
            if(item.getShape() == Shape.SPHERE){
                badItems.add(item);
                continue;
            }
            //
            if (((y+item.getHeight()+drawInterval<boxMayY)&&!(x+item.getWidth()+drawInterval<boxMaxX))){
                x = boxX + drawInterval;
                y = y + maxHeight+drawInterval;
                maxHeight = 0;
            }
            if(x+item.getWidth()+drawInterval<boxMaxX){
                item.draw(writer, x,y);
                if(maxHeight<y) maxHeight=item.getHeight();
                x = x+item.getWidth()+drawInterval;
            }
            //
        }
        for(Item item:badItems){
            if (((y+item.getHeight()+drawInterval<boxMayY)&&!(x+item.getWidth()+drawInterval<boxMaxX))){
                x = boxX + drawInterval;
                y = y + maxHeight+drawInterval;
                maxHeight = 0;
            }
            if(x+item.getWidth()+drawInterval<boxMaxX){
                item.draw(writer, x,y);
                if(maxHeight<y) maxHeight=item.getHeight();
                x = x+item.getWidth()+drawInterval;
            }
        }
    }
}
