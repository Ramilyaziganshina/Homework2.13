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

@ContextConfiguration(classes = {IntegerListImpl.class})
@ExtendWith(SpringExtension.class)
class IntegerListImplTest {

    @Autowired
    public IntegerList IntegerList;

    @Test
    void addByItem_success() {
        Integer item = 1;
        Integer expectedInteger = item;

        Integer actualInteger = IntegerList.add(item);
        Assertions.assertEquals(expectedInteger, actualInteger);
        IntegerList.clear();
    }

    @Test
    void addByIndexAndItem_success() {
        Integer item = 2;
        int index = 0;

        IntegerList.add(1);

        Integer expectedResult = item;
        Integer actualResult = IntegerList.add(index, item);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void addByIndexAndItem_throwInvalidIndexException() {
        Integer item = 2;
        int index = 5;

        IntegerList.add(1);

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    IntegerList.add(index, item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        IntegerList.clear();
    }

    @Test
    void set_success() {
        Integer item = 2;
        int index = 0;

        IntegerList.add(1);

        Integer expectedResult = item;
        Integer actualResult = IntegerList.set(index, item);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void set_throwInvalidIndexException() {
        Integer item = 2;
        int index = 5;

        IntegerList.add(1);

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    IntegerList.set(index, item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        IntegerList.clear();
    }

    @Test
    void removeByItem_success() {
        Integer item = 1;
        Integer expectedInteger = item;

        IntegerList.add(item);
        Integer actualInteger = IntegerList.remove(item);
        Assertions.assertEquals(expectedInteger, actualInteger);
        IntegerList.clear();
    }

    @Test
    void removeByItem_throwElementNotFoundException() {
        Integer item = 2;

        String expectedResult = "такой элемент не найден";
        Exception exception = assertThrows(
                ElementNotFoundException.class,
                () -> {
                    IntegerList.remove(item);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        IntegerList.clear();
    }

    @Test
    void removeByIndex_success() {
        Integer item = 1;
        int index = 0;
        Integer expectedInteger = item;

        IntegerList.add(item);
        Integer actualInteger = IntegerList.remove(index);
        Assertions.assertEquals(expectedInteger, actualInteger);
        IntegerList.clear();
    }

    @Test
    void removeByIndex_throwInvalidIndexException() {
        Integer item = 1;
        int index = 5;

        IntegerList.add(item);
        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(
                InvalidIndexException.class,
                () -> {
                    IntegerList.remove(index);
                }
        );

        Assertions.assertEquals(expectedResult, exception.getMessage());
        IntegerList.clear();
    }

    @Test
    void contains_true() {
        IntegerList.add(1);
        IntegerList.add(2);

        Assertions.assertTrue(IntegerList.contains(2));
        IntegerList.clear();
    }

    @Test
    void contains_false() {
        IntegerList.add(1);
        IntegerList.add(2);

        Assertions.assertFalse(IntegerList.contains(3));
        IntegerList.clear();
    }

    @Test
    void indexOf_success() {
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        int expectedResult = 1;
        int actualResult = IntegerList.indexOf(2);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void indexOf_notFound() {
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        int expectedResult = -1;
        int actualResult = IntegerList.indexOf(5);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void lastIndexOf_success() {
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        int expectedResult = 1;
        int actualResult = IntegerList.lastIndexOf(2);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void lastIndexOf_notFound() {
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        int expectedResult = -1;
        int actualResult = IntegerList.lastIndexOf(5);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void get_success() {
        int index = 1;
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        Integer expectedResult = 2;
        Integer actualResult = IntegerList.get(index);

        Assertions.assertEquals(expectedResult, actualResult);
        IntegerList.clear();
    }

    @Test
    void get_throwInvalidIndexException() {
        int index = 5;
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        String expectedResult = "индекс выходит за пределы массива";
        Exception exception = assertThrows(InvalidIndexException.class, () -> {
            IntegerList.get(index);
        });

        Assertions.assertEquals(expectedResult, exception.getMessage());
        IntegerList.clear();
    }

    @Test
    void equals_True() {
        Integer[] newIntegerList = {1, 2, 3};

        IntegerList.add(0, 1);
        IntegerList.add(1, 2);
        IntegerList.add(2, 3);

        Assertions.assertEquals(newIntegerList[0], IntegerList.get(0));
        Assertions.assertEquals(newIntegerList[1], IntegerList.get(1));
        Assertions.assertEquals(newIntegerList[2], IntegerList.get(2));

        IntegerList.clear();
    }

    @Test
    void size_success() {
        IntegerList.add(1);
        IntegerList.add(2);
        IntegerList.add(3);

        int expectedSize = 3;
        int actualSize = IntegerList.size();

        Assertions.assertEquals(expectedSize, actualSize);
        IntegerList.clear();
    }

    @Test
    void isEmpty_true() {
        Assertions.assertTrue(IntegerList.isEmpty());
    }

    @Test
    void isEmpty_false() {
        IntegerList.add(1);
        Assertions.assertFalse(IntegerList.isEmpty());
        IntegerList.clear();
    }

    @Test
    void clear_success() {
        IntegerList.add(1);
        IntegerList.clear();
        Assertions.assertTrue(IntegerList.isEmpty());
    }

    @Test
    void sort_success() {
        Integer[] arr = {5, 3, 8};
        Integer[] exceptionResult = {3, 5, 8};
        IntegerList.add(5);
        IntegerList.add(3);
        IntegerList.add(8);

        Integer[] actualResult = IntegerList.sort(arr);

        Assertions.assertEquals(exceptionResult[0], actualResult[0]);
        Assertions.assertEquals(exceptionResult[1], actualResult[1]);
        Assertions.assertEquals(exceptionResult[2], actualResult[2]);

        IntegerList.clear();
    }
}