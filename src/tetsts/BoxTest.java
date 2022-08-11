package tetsts;

import exceptions.*;
import items.Apple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storages.Box;

public class BoxTest {
    private Box testBox;
    private Apple apple;
    private Apple heavyApple;
    private Apple tooHaevyAplle;

    @BeforeEach
    void initBox(){
        testBox = Box.createDefaultBox();
        apple = Apple.creatDefaultApple();
        heavyApple = Apple.createApple("red",5,1);
        tooHaevyAplle = Apple.createApple("red",5,1);
        testBox.put(apple);
        testBox.put(heavyApple);
    }


    @Test
    void testWeight(){
        Assertions.assertEquals(7d, testBox.getWeight());
        for(int i = 0; i<30; i++){
            testBox.put(Box.createDefaultBox());
        }
        Assertions.assertEquals(37d, testBox.getWeight());

        StorageLimitException limitStorage = Assertions.assertThrows(StorageLimitException.class, () -> {
            for (int i = 0; i < 10; i++) {
                testBox.put(Apple.createApple("white", 4d, 1));
            }
        });
    }
    @Test
    void testPut(){
        SameItemException sameItem = Assertions.assertThrows(SameItemException.class, () -> {
            testBox.put(testBox);
        });
        ItemAlreadyStoredException itemAlready = Assertions.assertThrows(ItemAlreadyStoredException.class, () -> {
            testBox.put(apple);
        });
    }
    @Test
    void testGet(){
        Assertions.assertEquals(apple, testBox.getItem(0));
        Assertions.assertEquals(heavyApple, testBox.getItem(1));
        Assertions.assertEquals(heavyApple, testBox.getItemByColor("red"));
        Assertions.assertEquals(apple, testBox.getItemByName("apple"));
        ItemNotFoundException notFound = Assertions.assertThrows(ItemNotFoundException.class, () -> {
            testBox.getItemByColor("white");
        });
    }
    @Test
    void showContent(){
        Assertions.assertEquals("Name: box\n" +
                "Color: black\n" +
                "Shape: Cube\n" +
                "Weight: 7.0\n" +
                "Size: 10\n" +
                "Stored: false\n", testBox.toString());
    }

}
