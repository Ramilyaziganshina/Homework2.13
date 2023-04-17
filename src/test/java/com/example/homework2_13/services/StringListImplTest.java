package com.example.homework2_13.services;

import com.example.homework2_13.exceptions.ElementNotFoundException;
import com.example.homework2_13.exceptions.InvalidIndexException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {StringListImpl.class})
@ExtendWith(SpringExtension.class)
class StringListImplTest {

    @Autowired
    public StringList stringList;

    @Test
    void addByItem_success() {
        String item = "1";
        String expectedString = item;

        String actualString = stringList.add(item);
        Assertions.assertEquals(expectedString, actualString);
        stringList.clear();
    }

    @Test
    void addByIndexAndItem_success() {
        String item = "2";
        int index = 0;

        stringList.add("1");

        String expectedResult = item;
        String actualResult = stringList.add(index, item);

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void addByIndexAndItem_throwInvalidIndexException() {
        String item = "2";
        int index = 5;

        stringList.add("1");

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    stringList.add(index, item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        stringList.clear();
    }

    @Test
    void set_success() {
        String item = "2";
        int index = 0;

        stringList.add("1");

        String expectedResult = item;
        String actualResult = stringList.set(index, item);

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void set_throwInvalidIndexException() {
        String item = "2";
        int index = 5;

        stringList.add("1");

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    stringList.set(index, item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        stringList.clear();
    }

    @Test
    void removeByItem_success() {
        String item = "1";
        String expectedString = item;

        stringList.add(item);
        String actualString = stringList.remove(item);
        Assertions.assertEquals(expectedString, actualString);
        stringList.clear();
    }

    @Test
    void removeByItem_throwElementNotFoundException() {
        String item = "2";

        String expectedResult = "такой элемент не найден";
        Exception exception = assertThrows(
                ElementNotFoundException.class,
                () -> {
                    stringList.remove(item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        stringList.clear();
    }

    @Test
    void removeByIndex_success() {
        String item = "1";
        int index = 0;
        String expectedString = item;

        stringList.add(item);
        String actualString = stringList.remove(index);
        Assertions.assertEquals(expectedString, actualString);
        stringList.clear();
    }

    @Test
    void removeByIndex_throwInvalidIndexException() {
        String item = "1";
        int index = 5;

        stringList.add(item);
        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    stringList.remove(index);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        stringList.clear();
    }

    @Test
    void contains_true() {
        stringList.add("1");
        stringList.add("2");

        Assertions.assertTrue(stringList.contains("2"));
        stringList.clear();
    }

    @Test
    void contains_false() {
        stringList.add("1");
        stringList.add("2");

        Assertions.assertFalse(stringList.contains("3"));
        stringList.clear();
    }

    @Test
    void indexOf_success() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        int expectedResult = 1;
        int actualResult = stringList.indexOf("2");

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void indexOf_notFound() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        int expectedResult = -1;
        int actualResult = stringList.indexOf("5");

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void lastIndexOf_success() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        int expectedResult = 1;
        int actualResult = stringList.lastIndexOf("2");

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }


    @Test
    void lastIndexOf_notFound() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        int expectedResult = -1;
        int actualResult = stringList.lastIndexOf("5");

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void get_success() {
        int index = 1;
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        String expectedResult = "2";
        String actualResult = stringList.get(index);

        Assertions.assertEquals(expectedResult, actualResult);
        stringList.clear();
    }

    @Test
    void get_throwInvalodIndexException() {
        int index = 5;
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(InvalidIndexException.class, () -> {
            stringList.get(index);
        });

        Assertions.assertEquals(expectedResult, exception.getMessage());
        stringList.clear();
    }

    @Test
    void equals_True() {
        String[] newStringList = {"1", "2", "3"};

        stringList.add(0, "1");
        stringList.add(1, "2");
        stringList.add(2, "3");

        Assertions.assertEquals(newStringList[0], stringList.get(0));
        Assertions.assertEquals(newStringList[1], stringList.get(1));
        Assertions.assertEquals(newStringList[2], stringList.get(2));

        stringList.clear();
    }

    @Test
    void size_success() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");

        int expectedSize = 3;
        int actualSize = stringList.size();

        Assertions.assertEquals(expectedSize, actualSize);
        stringList.clear();
    }

    @Test
    void isEmpty_true() {
        Assertions.assertTrue(stringList.isEmpty());
    }

    @Test
    void isEmpty_false() {
        stringList.add("1");
        Assertions.assertFalse(stringList.isEmpty());
        stringList.clear();
    }

    @Test
    void clear_success() {
        stringList.add("1");
        stringList.clear();
        Assertions.assertTrue(stringList.isEmpty());
    }
}