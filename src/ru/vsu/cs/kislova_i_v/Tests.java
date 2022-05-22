package ru.vsu.cs.kislova_i_v;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class Tests {
    @Test
    public void testWithMyQueue() throws Exception {
        MyLinkedListQueue<Integer> queueFirst = new MyLinkedListQueue<>();
        int[] elementsFirst = {1, 2, 3, 4};
        enterElementsInMyQueue(queueFirst, elementsFirst.length, elementsFirst);
        MyLinkedListQueue<Integer> queueSecond = new MyLinkedListQueue<>();
        int[] elementsSecond = {5, 6, 7, 8, 9};
        enterElementsInMyQueue(queueSecond, elementsSecond.length, elementsSecond);

        MyLinkedListQueue<Integer> queueFirstFirst = new MyLinkedListQueue<>();
        MyLinkedListQueue<Integer> queueFirstSecond = new MyLinkedListQueue<>();
        Main.copyElementsInQueues(queueFirstFirst, queueFirstSecond, queueFirst);

        MyLinkedListQueue<Integer> queueSecondFirst = new MyLinkedListQueue<>();
        MyLinkedListQueue<Integer> queueSecondSecond = new MyLinkedListQueue<>();
        Main.copyElementsInQueues(queueSecondFirst, queueSecondSecond, queueSecond);

        MyLinkedListQueue<Integer> resultQueueFirst = Main.transformQueues(queueFirstFirst,
                queueSecondFirst, 1);
        MyLinkedListQueue<Integer> resultQueueSecond = Main.transformQueues(queueFirstSecond,
                queueSecondSecond, 2);

        Integer[] resultArrayFirst = toArrayForMyQueue(resultQueueFirst);
        Integer[] resultArraySecond = toArrayForMyQueue(resultQueueSecond);

        Integer[] correctArrayFirst = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] correctArraySecond = {5, 6, 7, 8, 9, 1, 2, 3, 4};

        Assert.assertArrayEquals(resultArrayFirst, correctArrayFirst);
        Assert.assertArrayEquals(resultArraySecond, correctArraySecond);
    }

    @Test
    public void testWithStandardQueue() throws Exception {
        LinkedBlockingQueue<Integer> queueFirst = new LinkedBlockingQueue<>();
        int[] elementsFirst = {115, 29, 0, -20, 49, 5, 0, -3};
        enterElementsInStandardQueue(queueFirst, elementsFirst.length, elementsFirst);
        LinkedBlockingQueue<Integer> queueSecond = new LinkedBlockingQueue<>();
        int[] elementsSecond = {549, 34, 52, -30, -5, 28, 0};
        enterElementsInStandardQueue(queueSecond, elementsSecond.length, elementsSecond);

        LinkedBlockingQueue<Integer> queueFirstFirst = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queueFirstSecond = new LinkedBlockingQueue<>();
        StandardMain.copyElementsInQueues(queueFirstFirst, queueFirstSecond, queueFirst);

        LinkedBlockingQueue<Integer> queueSecondFirst = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queueSecondSecond = new LinkedBlockingQueue<>();
        StandardMain.copyElementsInQueues(queueSecondFirst, queueSecondSecond, queueSecond);

        LinkedBlockingQueue<Integer> resultQueueFirst = StandardMain.transformQueues(queueFirstFirst,
                queueSecondFirst, 1);
        LinkedBlockingQueue<Integer> resultQueueSecond = StandardMain.transformQueues(queueFirstSecond,
                queueSecondSecond, 2);

        Integer[] resultArrayFirst = toArrayForStandardQueue(resultQueueFirst);
        Integer[] resultArraySecond = toArrayForStandardQueue(resultQueueSecond);

        Integer[] correctArrayFirst = {115, 29, 0, -20, 49, 5, 0, -3, 549, 34, 52, -30, -5, 28, 0};
        Integer[] correctArraySecond = {549, 34, 52, -30, -5, 28, 0, 115, 29, 0, -20, 49, 5, 0, -3};

        Assert.assertArrayEquals(resultArrayFirst, correctArrayFirst);
        Assert.assertArrayEquals(resultArraySecond, correctArraySecond);
    }

    private static void enterElementsInMyQueue(MyLinkedListQueue<Integer> queue, int countElements, int[] elements) {
        for (int i = 0; i < countElements; i++) {
            queue.addElement(elements[i]);
        }
    }

    private static void enterElementsInStandardQueue(LinkedBlockingQueue<Integer> queue, int countElements,
                                                     int[] elements) {
        for (int i = 0; i < countElements; i++) {
            queue.add(elements[i]);
        }
    }

    private static Integer[] toArrayForMyQueue(MyLinkedListQueue<Integer> listQueue) throws Exception {
        int size = listQueue.count();
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = Integer.valueOf(listQueue.removeFirstElement());
        }

        return array;
    }

    private static Integer[] toArrayForStandardQueue(LinkedBlockingQueue<Integer> listQueue) {
        int size = listQueue.size();
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = Integer.valueOf(listQueue.remove());
        }
        return array;
    }
}
