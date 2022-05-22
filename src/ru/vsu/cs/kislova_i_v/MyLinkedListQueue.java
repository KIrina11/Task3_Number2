package ru.vsu.cs.kislova_i_v;

public class MyLinkedListQueue<T> implements SimpleQueue<T> {

    private class LinkedListQueueNode {
        public T value;
        public LinkedListQueueNode next;

        public LinkedListQueueNode(T value, LinkedListQueueNode next) {
            this.value = value;
            this.next = next;
        }

        public LinkedListQueueNode(T value) {
            this(value, null);
        }
    }

    private LinkedListQueueNode head = null;
    private LinkedListQueueNode tail = null;
    private int count = 0;

    @Override
    public void addElement(T value) {
        if (count == 0) {
            head = tail = new LinkedListQueueNode(value);
        } else {
            tail.next = new LinkedListQueueNode(value);
            tail = tail.next;
        }
        count++;
    }

    @Override
    public T removeFirstElement() throws Exception {
        T result = getFirstElement();
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return result;
    }

    @Override
    public T removeLastElement() throws Exception {
        T result = getLastElement();
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return result;
    }

    @Override
    public T getFirstElement() throws Exception {
        if (count == 0) {
            throw new Exception("Queue is empty");
        }
        return head.value;
    }

    @Override
    public T getLastElement() throws Exception {
        if (count == 0) {
            throw new Exception("Queue is empty");
        }
        return tail.value;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count() == 0;
    }
}
