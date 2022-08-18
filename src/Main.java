import items.Apple;
import items.Brick;
import storages.Bag;
import storages.Box;
import storages.Stack;
import utils.SVGWriter;
import java.io.IOException;

public class Main {

    public static void main (String[] args){
        Apple apple = Apple.creatDefaultApple();
        Brick brick = Brick.createDefaultBrick();
        Box box = Box.createDefaultBox();
        Box megaBox = Box.createBox("megaBox", "antiquewhite", 32);
        box.put(Apple.creatDefaultApple());
        for(int i = 0; i<36; i++){
            box.put(Brick.createDefaultBrick());
        }
        Bag bag = Bag.createDefaultBag();

        box.put(Bag.createDefaultBag());
        Stack stack = Stack.createStack();

        stack.put(box);
        for(int i = 0; i<4; i++){
            stack.put(Brick.createDefaultBrick());
        }
        System.out.println(box.getContent().size());

       // megaBox.put(box);
        try(SVGWriter writer = new SVGWriter();) {

            //box.draw2(writer, 100,100);

            stack.draw(writer, 200, 200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
