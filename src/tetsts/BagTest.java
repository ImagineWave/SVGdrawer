package tetsts;

import exceptions.ItemAlreadyStoredException;
import exceptions.ItemNotFoundException;
import exceptions.SameItemException;
import exceptions.StorageLimitException;
import items.Apple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storages.Bag;

public class BagTest {
    private Bag testBag;
    private Apple apple;
    private Apple heavyApple;
    private Apple tooHaevyAplle;

    @BeforeEach
    void initBox(){
        testBag = Bag.createDefaultBag();
        apple = Apple.creatDefaultApple();
        heavyApple = Apple.createApple("red",2,1);
        tooHaevyAplle = Apple.createApple("yellow",5,1);
        testBag.put(apple);
        testBag.put(heavyApple);
    }


    @Test
    void testWeight(){
        Assertions.assertEquals(4d, testBag.getWeight());

        StorageLimitException limitStorage = Assertions.assertThrows(StorageLimitException.class, () -> {
            for (int i = 0; i < 10; i++) {
                testBag.put(Apple.createApple("white", 4d, 1));
            }
        });
    }
    @Test
    void testPut(){
        SameItemException sameItem = Assertions.assertThrows(SameItemException.class, () -> {
            testBag.put(testBag);
        });
        ItemAlreadyStoredException itemAlready = Assertions.assertThrows(ItemAlreadyStoredException.class, () -> {
            testBag.put(apple);
        });
    }
    @Test
    void testGet(){
        Assertions.assertEquals(apple, testBag.getItem(0));
        Assertions.assertEquals(heavyApple, testBag.getItem(1));
        Assertions.assertEquals(heavyApple, testBag.getItemByColor("red"));
        Assertions.assertEquals(apple, testBag.getItemByName("apple"));
        ItemNotFoundException notFound = Assertions.assertThrows(ItemNotFoundException.class, () -> {
            testBag.getItemByColor("white");
        });
    }
    @Test
    void testRemove(){
        Assertions.assertEquals(4d, testBag.getWeight());
        Assertions.assertEquals(apple, testBag.remove(apple));
        Assertions.assertEquals(3d, testBag.getWeight());
        Assertions.assertEquals(heavyApple, testBag.remove(testBag.getItemByColor("red")));
        Assertions.assertEquals(1d, testBag.getWeight());
    }
    @Test
    void showContent(){
        Assertions.assertEquals(
                "Name: bag\n" +
                "Color: burlywood\n" +
                "Shape: Sphere\n" +
                "Weight: 4.0\n" +
                "Size: 1\n" +
                "Stored: false\n", testBag.toString());
    }
}
