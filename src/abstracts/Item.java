package abstracts;

import utils.SVGWriter;

import java.io.IOException;

public abstract class Item{

    public Item(String name, String color, Shape shape, double weight, int size) {
        checkSize();
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.weight = weight;
        this.size = size;
    }
    public Item (){

    }
    private String name = "nameless";
    private String color = "white";
    private Shape shape = Shape.SPHERE;
    private double weight = 1;
    private int size = 1;

    public boolean isInStorage() {
        return inStorage;
    }

    public void setInStorage(boolean inStorage) {
        this.inStorage = inStorage;
    }

    private boolean inStorage = false;

    public double getWeight() {
        return weight;
    }
    public Shape getShape() {
        return shape;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
    @Override
    public String toString(){
    	String result = "";
    	result+="Name: "+this.getName()+"\n";
    	result+="Color: "+this.getColor()+"\n";
    	result+="Shape: "+this.getShape().toString()+"\n";
    	Double d = this.getWeight();
    	result+="Weight: "+d.toString()+"\n";
    	Integer s = this.getSize();
    	result+="Size: "+s.toString()+"\n";
    	Boolean b = this.isInStorage();
    	result+="Stored: "+b.toString()+"\n";
    	return result;
    }
    protected void checkSize(){
        if(size>5d||size<0d){
            throw new IllegalArgumentException("size cannot be more than 5 and less than 0");
        }
    }
    public int getHeight(){ //
        return size*50;
    }
    public int getWidth(){
        return size*100;
    }
    public void draw(SVGWriter writer, int x, int y) throws IOException {
        switch (shape) {
            case SPHERE: {
                writer.writeRound(x+this.getWidth(),y+this.getWidth(),this.getWidth(), color);
                break;
            }
            case CUBE: {
                writer.writeRect(x,y, this.getWidth(),this.getHeight(),color); //(int x,int y,int width,int height,String color)
                break;
            }
            case FLAT:{
                writer.writeFlat();
                break;
            }
        }
    }
}
