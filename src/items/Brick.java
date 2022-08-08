package items;

import abstracts.Item;
import abstracts.Shape;

public class Brick extends Item {
    public Brick (String name, String color, Shape shape, double weight, int size){
        super(name, color, shape, weight, size);
    }
    public static Brick createDefaultBrick() {
        Brick brick = new Brick("brick", "brown", Shape.CUBE,  1.5d, 1);
        return brick;
    }
    public static Brick createBrick(String color, int size) {
        Brick brick = new Brick("brick", color, Shape.CUBE,  size*1.5, size);
        return brick;
    }

}
