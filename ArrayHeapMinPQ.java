package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>, Comparable<T> {

    public ArrayList<T> contentList;
    public ArrayList<Double> priorityList;
    int size;

    @Override
    public int compareTo(T other) {
        return this.compareTo(other);
    }

    public ArrayHeapMinPQ() {
        this.contentList = new ArrayList<T>();
        this.priorityList = new ArrayList<Double>();
        this.size = 0;

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

        this.size = 1;
    }


    public void add(T input, double priority) {

        if (this.contains(input)) {
            throw new IllegalArgumentException("The item already existed.");
        } else {
            this.contentList.add(input);
            this.priorityList.add(priority);
            this.size++;
        }

        while (input.compareTo(contentList.get(this.getParent(input))) > 0) {
            this.swim(input);
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
    //TODO while use the swim, pay attention to set the condition
    public T removeSmallest() {
        T result = this.getSmallest();
        T rightest = this.getRightest();
        this.contentList.set(1, rightest);
        this.contentList.remove(rightest);

        while (leftLarger(rightest) || rightLarger(rightest)) {
            if (leftLarger(rightest)) {
                swim(this.contentList.get(this.getLeft(rightest)));
            } else {
                swim(this.contentList.get(this.getRight(rightest)));
            }
        }

        return result;
    }

    public void changePriority(T item, double priorty) {
        int inde = this.contentList.indexOf(item);
        this.priorityList.set(inde, priorty);
    }


    public int size() {
        return this.size - 1;
    }


    //==============Helper method===================\\

    //Get the parent's index
    private int getParent(T item) {
        int ind = this.contentList.indexOf(item);
        return ind / 2;
    }

    private int getLeft(T item) {
        return this.contentList.indexOf(item) * 2;
    }

    private int getRight(T item) {
        return getLeft(item) + 1;
    }

    private void swim(T item) {
        T parent = this.contentList.get(getParent(item));
        exch(item, parent);
    }

    private void leftSink(T item) {
        exch(item, this.contentList.get(this.getLeft(item)));
    }

    private void rightSink(T item) {
        exch(item, this.contentList.get(this.getRight(item)));
    }

    private void exch(T a, T b) {
        int aIndx = this.contentList.indexOf(a);
        int bIndx = this.contentList.indexOf(b);

        this.contentList.set(aIndx, b);
        this.contentList.set(bIndx, a);

        double aPri = this.priorityList.get(aIndx);
        double bPri = this.priorityList.get(aIndx);

        this.priorityList.set(aIndx, bPri);
        this.priorityList.set(bIndx, aPri);

    }

    private T prev(T input) {
        int index = this.contentList.indexOf(input);
        return this.contentList.get(index - 1);

    }

    private T next(T input) {
        int index = this.contentList.indexOf(input);
        return this.contentList.get(index + 1);
    }

    private double getPri(T item) {
        int index = this.contentList.indexOf(item);
        return this.priorityList.get(index);
    }

    private T getRightest() {
        int ind = this.contentList.size();
        return this.contentList.get(ind);
    }


    private boolean leftLarger(T item) {
        T child = this.contentList.get(this.getLeft(item));

        return item.compareTo(child) > 0;
    }

    private boolean rightLarger(T item) {
        T child = this.contentList.get(this.getRight(item));

        return item.compareTo(child) > 0;
    }
}

