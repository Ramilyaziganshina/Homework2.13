package com.example.homework2_13.services;

import com.example.homework2_13.exceptions.ElementNotFoundException;
import com.example.homework2_13.exceptions.InvalidIndexException;
import com.example.homework2_13.exceptions.NullItemException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        storage = new Integer[initSize];
    }

    @Override
    /**
     * Добавление элемента.
     * Возвращает добавленный элемент.
     *
     * @param item элемент, подлежащий добавлению
     */
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    /**
     * Добавление элемента.
     * Возвращает добавленный элемент.
     *
     * @param item элемент, подлежащий удалению
     * @param index его индекс
     */
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item;
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    /**
     * Замена элемента на указанной позиции.
     * Возвращает новый элемент.
     *
     * @exception InvalidIndexException бросает исключение, если элемент надлежит записать под индексом за пределами массива
     * @param item элемент, записываемый в массив
     * @param index индекс, куда следует записать элемент
     */
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    /**
     * Удаление элемента.
     * Возвращает удаленный элемент.
     *
     * @exception ElementNotFoundException бросает исключение, если элемент не найден
     * @param item элемент, подлежащий удалению
     */
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("такой элемент не найден");
        }

        return remove(index);
    }

    @Override
    /**
     * Удаление элемента.
     * Возвращает удаленный элемент.
     *
     * @exception ElementNotFoundException бросает исключение, если элемент не найден
     * @param index место элемента, подлежащего удалению
     */
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    /**
     * Проверка элемента на существование в этом массиве.
     * Возвращает true, если элемент существует, false если элемент не найден.
     *
     * @param item элемент, подлежащий поиску
     */
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    /**
     * Поиск элемента.
     * Возвращает индекс найденного элемента, либо -1 если элемент не найден.
     *
     * @param item элемент, подлежащий поиску
     */
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    /**
     * Поиск элемента с конца масива.
     * Возвращает индекс найденного элемента, либо -1 если элемент не найден.
     *
     * @param item элемент, подлежащий поиску
     */
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    /**
     * Получение элемента.
     * Возвращает элемент за заданным индексом.
     *
     * @exception InvalidIndexException бросает исключение, если элемент выходит за рамки заданного массива
     * @param index индекс искомого элемента
     */
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    /**
     * Сравнивает текущий массив с другим.
     * Возвращает true если массивы идентичны, false если массивы разные.
     *
     * @exception NullItemException бросает исключение, если вместо массива передан null
     * @param overList массив, который нужно сравнить с текущим
     */
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    /**
     * Определение размера массива.
     * Возвращает размер массива.
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * Проверка массива на содержание каких-либо элементов.
     * Возвращает true, если элементов в списке нет, иначе false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /**
     * Удаление всех элементов в массиве.
     * ничего не возвращает
     */
    public void clear() {
        size = 0;
    }

    @Override
    /**
     * Копирование массива.
     * Возвращает копию массива.
     */
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * Проверка элемента.
     * Ничего не возвращает.
     *
     * @exception NullItemException бросает исключение, если заданный элемент пустой
     * @param item элемент, подлежащий проверке
     */
    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("строка не может быть пустой");
        }
    }

    /**
     * Увеличение массива, если в этом есть необходимость.
     * Ничего не возвращает.
     */
    private void growIfNeeded() {
        if (size == storage.length) {
            grow();
        }
    }

    /**
     * Проверка индекса элемента.
     * Ничего не возвращает.
     *
     * @exception InvalidIndexException бросает исключение, если индекс элемента выходит за рамки массива
     * @param index индекс, подлежащий проверке
     */
    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("индекс выходит за пределы массива");
        }
    }

    /**
     * Сортировка массива.
     * Ничего не возвращает.
     *
     * @param arr массив, подлежащий сортировке
     */
    private void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, size + size / 2);
    }

    @Override
    public Integer[] sort1(Integer[] arr) {
        sort(arr);
        return arr;
    }
}