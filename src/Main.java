import items.Apple;
import items.Brick;
import storages.Bag;
import storages.Box;
import utils.SVGWriter;
import java.util.regex.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Apple apple = Apple.creatDefaultApple();
        Brick brick = Brick.createDefaultBrick();
        Box box = Box.createDefaultBox();
        Box megaBox = Box.createBox("megaBox", "antiquewhite", 32);
        for(int i = 0; i<20; i++){
            box.put(Brick.createDefaultBrick());
        }
        System.out.println(box.getContent().size());

       // megaBox.put(box);
        try(SVGWriter writer = new SVGWriter();) {

            box.draw(writer, 100,100);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
