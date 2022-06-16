package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>, Comparable<T> {

    public ArrayList<T> contentList;
    public ArrayList<Double> priorityList;

    @Override
    public int compareTo(T other) {
        return this.compareTo(other);
    }

    public ArrayHeapMinPQ() {
        this.contentList = new ArrayList<T>();
        this.priorityList = new ArrayList<Double>();

        contentList.add(null);
        priorityList.add(null);
    }

    public ArrayHeapMinPQ(T input, double pri) {
        this.contentList = new ArrayList<T>();
        this.priorityList = new ArrayList<Double>();

        contentList.add(null);
        priorityList.add(null);

        contentList.add(input);
        priorityList.add(pri);
    }

    //TODO need to compare the added values with existed values.
    public void add(T input, double priority) {

        if (this.contains(input)) {
            throw new IllegalArgumentException("The item already existed.");
        } else {
            this.contentList.add(input);
            this.priorityList.add(priority);
        }
        

    }

    public boolean contains(T item) {
        return contentList.contains(item);
    }

    public T getSmallest() {
        if (this.contentList.isEmpty()) {
            throw new NoSuchElementException("The Queue is Empty.");
        } else {
            return this.contentList.get(1);
        }
    }

    //TODO first need to finish the add method
    public T removeSmallest() {
        T result = this.getSmallest();


        return result;
    }


    public int size() {
        return this.contentList.size() - 1;
    }


    //==============Helper method===================\\
    private void swim(T item) {
        T before = this.contentList.get(contentList.indexOf(item) - 1);
        exch(item, before);
    }

    private void exch(T a, T b) {
        int aIndx = this.contentList.indexOf(a);
        int bIndx = this.contentList.indexOf(b);

        this.contentList.set(aIndx, b);
        this.contentList.set(bIndx, a);

        int aPri = this.priorityList.indexOf(a);
        int bPri = this.priorityList.indexOf(b);

        this.priorityList.set(aPri, this.priorityList.get(bPri));
        this.priorityList.set(bPri, this.priorityList.get(aPri));

    }

}

