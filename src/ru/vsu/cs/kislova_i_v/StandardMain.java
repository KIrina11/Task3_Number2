package ru.vsu.cs.kislova_i_v;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class StandardMain {

    public static void standardMain() throws Exception {
        LinkedBlockingQueue<Integer> queueFirst = new LinkedBlockingQueue<>();
        addElementsInQueue(queueFirst, 1);
        LinkedBlockingQueue<Integer> queueSecond = new LinkedBlockingQueue<>();
        addElementsInQueue(queueSecond, 2);

        LinkedBlockingQueue<Integer> queueFirstFirst = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queueFirstSecond = new LinkedBlockingQueue<>();
        copyElementsInQueues(queueFirstFirst, queueFirstSecond, queueFirst);

        LinkedBlockingQueue<Integer> queueSecondFirst = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Integer> queueSecondSecond = new LinkedBlockingQueue<>();
        copyElementsInQueues(queueSecondFirst, queueSecondSecond, queueSecond);

        LinkedBlockingQueue<Integer> resultQueueFirst = transformQueues(queueFirstFirst,
                queueSecondFirst, 1);
        LinkedBlockingQueue<Integer> resultQueueSecond = transformQueues(queueFirstSecond,
                queueSecondSecond, 2);

        printListQueue(resultQueueFirst, 1);
        printListQueue(resultQueueSecond, 2);
    }

    private static void addElementsInQueue(LinkedBlockingQueue<Integer> listQueue, int numberQueue) {
        if (numberQueue == 1) {
            System.out.println("Введите количество элементов первой очереди (натуральное число): ");
        } else {
            System.out.println("Введите количество элементов второй очереди (натуральное число): ");
        }
        Scanner scanner = new Scanner(System.in);
        int countOfElements = scanner.nextInt();

        if (numberQueue == 1) {
            System.out.println("Введите " + countOfElements + " элементов первой очереди: ");
        } else {
            System.out.println("Введите " + countOfElements + " элементов второй очереди: ");
        }
        for (int i = 0; i < countOfElements; i++) {
            int element = scanner.nextInt();
            listQueue.add(element);
        }
    }

    public static LinkedBlockingQueue<Integer> transformQueues(LinkedBlockingQueue<Integer> queueFirst,
                                                               LinkedBlockingQueue<Integer> queueSecond,
                                                               int numberResultQueue) throws Exception {
        LinkedBlockingQueue<Integer> queueFirstListFirst = new LinkedBlockingQueue<>(); //очередь 1.1
        LinkedBlockingQueue<Integer> queueFirstListSecond = new LinkedBlockingQueue<>(); //очередь 1.2

        copyElementsInQueues(queueFirstListFirst, queueFirstListSecond, queueFirst);

        LinkedBlockingQueue<Integer> queueSecondListFirst = new LinkedBlockingQueue<>(); //очередь 2.1
        LinkedBlockingQueue<Integer> queueSecondListSecond = new LinkedBlockingQueue<>(); //очередь 2.2

        copyElementsInQueues(queueSecondListFirst, queueSecondListSecond, queueSecond);

        LinkedBlockingQueue<Integer> resultQueueFirst = formResultQueue(queueFirstListFirst, queueSecondListFirst);
        LinkedBlockingQueue<Integer> resultQueueSecond = formResultQueue(queueSecondListSecond, queueFirstListSecond);

        if (numberResultQueue == 1) {
            return resultQueueFirst;
        } else {
            return resultQueueSecond;
        }
    }

    public static void copyElementsInQueues(LinkedBlockingQueue<Integer> queueListFirst,
                                            LinkedBlockingQueue<Integer> queueListSecond,
                                            LinkedBlockingQueue<Integer> queue) throws Exception {
        int count = queue.size();
        for (int i = 0; i < count; i++) { //создаю 2 очереди 1.1 и 1.2 (или 2.1 и 2.2) и перемещаю в них значения 1 (или 2) очереди
            queueListFirst.add(queue.peek());
            queueListSecond.add(queue.poll());
        }
    }

    private static LinkedBlockingQueue<Integer> formResultQueue(LinkedBlockingQueue<Integer> queueFirst,
                                                                LinkedBlockingQueue<Integer> queueSecond) throws Exception {
        int count = queueSecond.size();
        for (int i = 0; i < count; i++) {
            queueFirst.add(queueSecond.poll());
        }

        return queueFirst;
    }

    private static void printListQueue(LinkedBlockingQueue<Integer> listQueue, int numberQueue) throws Exception {
        if (numberQueue == 1) {
            System.out.print("Первая очередь: ");
        } else {
            System.out.print("Вторая очередь: ");
        }

        int count = listQueue.size();
        for (int i = 0; i < count - 1; i++) {
            System.out.print(listQueue.poll() + ", ");
        }
        System.out.println(listQueue.poll());
    }
}
