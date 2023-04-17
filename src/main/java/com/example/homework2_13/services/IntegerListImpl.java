package com.example.homework2_13.services;

import com.example.homework2_13.exceptions.ElementNotFoundException;
import com.example.homework2_13.exceptions.InvalidIndexException;
import com.example.homework2_13.exceptions.NullItemException;
import com.example.homework2_13.exceptions.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        storage = new Integer[initSize];
    }

    @Override
    // Добавление элемента.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public Integer add(int index, Integer item) {
        validateSize();
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
    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение, если индекс больше фактического количества элементов или выходит за пределы массива.
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    // Удаление элемента.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("такой элемент не найден");
        }

        return remove(index);
    }

    @Override
    // Удаление элемента по индексу.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
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
    // Проверка на существование элемента.
    // Вернуть true/false;
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sortInsertion(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    // Поиск элемента.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Поиск элемента с конца.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Получить элемент по индексу.
    // Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    // Вернуть фактическое количество элементов.
    public int size() {
        return size;
    }

    @Override
    // Вернуть true, если элементов в списке нет, иначе false.
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Удалить все элементы из списка.
    public void clear() {
        size = 0;
    }

    @Override
    // Создать новый массив из строк в списке и вернуть его.
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("строка не может быть пустой");
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException("в массиве нет больше мест");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("индекс выходит за пределы массива");
        }
    }

//    private static void swapElements(int[] arr, int indexA, int indexB) {
//        int tmp = arr[indexA];
//        arr[indexA] = arr[indexB];
//        arr[indexB] = tmp;
//    }

    private static Integer[] sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
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

    @Override
    public Integer[] sort(Integer[] arr) {
        sortInsertion(arr);
        return arr;
    }
}