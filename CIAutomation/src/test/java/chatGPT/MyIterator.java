package chatGPT;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private T[] array;
    private int index;

    public MyIterator(T[] array) {
        this.array = array;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < array.length;
    }

    public T next() {
        T value = array[index];
        index++;
        return value;
    }
}

