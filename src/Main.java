import items.Apple;
import items.Brick;
import storages.Bag;
import storages.Box;
import utils.SVGWriter;

import java.io.IOException;

public class Main {
    public static void main (String[] args){
        Apple apple = Apple.creatDefaultApple();
        Brick brick = Brick.createDefaultBrick();
        Box box = Box.createDefaultBox();
        Box megaBox = Box.createBox("megaBox", "antiquewhite", 32);
        Bag bag = Bag.createDefaultBag();
        megaBox.put(Brick.createDefaultBrick());
        for(int i = 0; i<100; i++){
            megaBox.put(Apple.creatDefaultApple());
        }
        megaBox.put(Bag.createDefaultBag());
        megaBox.put(Bag.createDefaultBag());
        for(int i = 0; i<100; i++){
            megaBox.put(Brick.createDefaultBrick());
        }


        for(int i = 0; i<310; i++){
           box.put(Apple.creatDefaultApple());
           //box.put(Brick.createDefaultBrick());
        }
       // megaBox.put(box);
        try(SVGWriter writer = new SVGWriter();) {

            megaBox.draw(writer, 100,100);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
