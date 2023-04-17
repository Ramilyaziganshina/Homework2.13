package com.example.homework2_13.services;

import com.example.homework2_13.exceptions.ElementNotFoundException;
import com.example.homework2_13.exceptions.InvalidIndexException;
import com.example.homework2_13.exceptions.NullItemException;
import com.example.homework2_13.exceptions.StorageIsFullException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] storage;
    private int size;

    public StringListImpl() {
        storage = new String[10];
    }

    public StringListImpl(int initSize) {
        storage = new String[initSize];
    }

    @Override
    // Добавление элемента.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public String add(String item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    // Удаление элемента.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public String remove(String item) {
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
    public String remove(int index) {
        validateIndex(index);
        String item = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    // Проверка на существование элемента.
    // Вернуть true/false;
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    // Поиск элемента.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int indexOf(String item) {
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
    public int lastIndexOf(String item) {
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
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(String item) {
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
}