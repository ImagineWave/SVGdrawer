import abstracts.Item;
import items.Apple;
import items.Brick;
import storages.Bag;
import storages.Box;
import storages.Stack;
import utils.SVGWriter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main (String[] args){
        Apple apple = Apple.creatDefaultApple();
        Brick brick = Brick.createDefaultBrick();
        Box box = Box.createDefaultBox();

        box.put(brick);
        for(int i = 0; i<15; i++){
            box.put(Brick.createDefaultBrick());
        }
        for(int i = 0; i<99; i++){
            box.put(Apple.creatDefaultApple());
        }

        try(SVGWriter writer = new SVGWriter();) {

            box.draw(writer, 100,100);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
