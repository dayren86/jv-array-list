package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] data;
    private int index;

    public ArrayList() {
        this.data = new Object[ARRAY_SIZE];
        index = 0;
    }

    @Override
    public void add(T value) {
        if (index == data.length - 1) {
            Object[] tempArray = new Object[(int) (data.length * 1.5)];
            for (int i = 0; i < data.length; i++) {
                tempArray[i] = data[i];
            }
            data = tempArray;
        }
        data[index] = value;
        index++;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index didn`t value");
        }

        Object[] tempArray = new Object[data.length];
        int tempIndex = 0;

        if (this.index == 0) {
            this.index++;
            data[0] = value;
        } else if (this.index == index) {
            data[index] = value;
            this.index++;
        } else {
            for (int i = 0; i < this.index; i++) {
                if (this.index == tempArray.length) {
                    Object[] tempArray2 = new Object[(int) (data.length * 1.5)];
                    tempArray = new Object[tempArray2.length];
                    for (int j = 0; j < data.length; j++) {
                        tempArray2[j] = data[j];
                    }
                    data = tempArray2;
                }

                if (index == i) {
                    tempArray[tempIndex] = value;
                    tempIndex++;
                }
                tempArray[tempIndex] = data[i];
                tempIndex++;
            }

            data = tempArray;
            this.index = tempIndex;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= this.index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value");
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= this.index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value");
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.index) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        final T temp = get(index);

        Object[] tempArray = new Object[data.length];
        int tempIndex = 0;

        for (int i = 0; i < data.length; i++) {
            if (i != index) {
                tempArray[tempIndex] = data[i];
                tempIndex++;
            }
        }
        data = tempArray;
        this.index--;
        return temp;
    }

    @Override
    public T remove(T element) {
        Object temp = new Object();
        boolean find = false;

        for (int i = 0; i < index + 1; i++) {
            if (Objects.equals(data[i], element)) {
                temp = data[i];
                remove(i);
                find = true;
                break;
            }
        }
        if (!find) {
            throw new NoSuchElementException();
        }
        return (T) temp;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        if (index == 0) {
            return true;
        }
        return false;
    }
}
