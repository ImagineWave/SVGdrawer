package items;

import abstracts.Item;
import abstracts.Shape;

public class Apple extends Item {
    public Apple(String name, String color, Shape shape, double weight, int size) {
        super(name, color, shape, weight, size);
    }
    public static Apple createApple(String color, double weight, int size){
        Apple apple = new Apple("apple",color,Shape.SPHERE,weight,size);
        return apple;
    }
    public static Apple creatDefaultApple(){
        Apple apple = new Apple("apple","green",Shape.SPHERE,1d,1);
        return apple;
    }
}
