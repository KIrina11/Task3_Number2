package ru.vsu.cs.kislova_i_v;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        MyLinkedListQueue<Integer> queueFirst = new MyLinkedListQueue<>();
        addElementsInQueue(queueFirst, 1);
        MyLinkedListQueue<Integer> queueSecond = new MyLinkedListQueue<>();
        addElementsInQueue(queueSecond, 2);

        MyLinkedListQueue<Integer> queueFirstFirst = new MyLinkedListQueue<>();
        MyLinkedListQueue<Integer> queueFirstSecond = new MyLinkedListQueue<>();
        copyElementsInQueues(queueFirstFirst, queueFirstSecond, queueFirst);

        MyLinkedListQueue<Integer> queueSecondFirst = new MyLinkedListQueue<>();
        MyLinkedListQueue<Integer> queueSecondSecond = new MyLinkedListQueue<>();
        copyElementsInQueues(queueSecondFirst, queueSecondSecond, queueSecond);

        System.out.println("Преобразование очередей, реализованное на основе LinkedList: ");

        MyLinkedListQueue<Integer> resultQueueFirst = transformQueues(queueFirstFirst, queueSecondFirst, 1);

        MyLinkedListQueue<Integer> resultQueueSecond = transformQueues(queueFirstSecond, queueSecondSecond, 2);

        printListQueue(resultQueueFirst, 1);
        printListQueue(resultQueueSecond, 2);

        System.out.println("Преобразование очередей, реализованное с использованием стандартной библиотеки языка Java: ");
        StandardMain.standardMain();
    }

    private static void addElementsInQueue(MyLinkedListQueue<Integer> listQueue, int numberQueue) {
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
            listQueue.addElement(element);
        }
    }

    public static MyLinkedListQueue<Integer> transformQueues(MyLinkedListQueue<Integer> queueFirst,
                                                             MyLinkedListQueue<Integer> queueSecond,
                                                             int numberResultQueue) throws Exception {
        MyLinkedListQueue<Integer> queueFirstListFirst = new MyLinkedListQueue<>(); //очередь 1.1
        MyLinkedListQueue<Integer> queueFirstListSecond = new MyLinkedListQueue<>(); //очередь 1.2

        copyElementsInQueues(queueFirstListFirst, queueFirstListSecond, queueFirst);

        MyLinkedListQueue<Integer> queueSecondListFirst = new MyLinkedListQueue<>(); //очередь 2.1
        MyLinkedListQueue<Integer> queueSecondListSecond = new MyLinkedListQueue<>(); //очередь 2.2

        copyElementsInQueues(queueSecondListFirst, queueSecondListSecond, queueSecond);

        MyLinkedListQueue<Integer> resultQueueFirst = formResultQueue(queueFirstListFirst, queueSecondListFirst);
        MyLinkedListQueue<Integer> resultQueueSecond = formResultQueue(queueSecondListSecond, queueFirstListSecond);

        if (numberResultQueue == 1) {
            return resultQueueFirst;
        } else {
            return resultQueueSecond;
        }
    }

    public static void copyElementsInQueues(MyLinkedListQueue<Integer> queueListFirst,
                                            MyLinkedListQueue<Integer> queueListSecond,
                                            MyLinkedListQueue<Integer> queue) throws Exception {
        int count = queue.count();
        for (int i = 0; i < count; i++) { //создаю 2 очереди 1.1 и 1.2 (или 2.1 и 2.2) и перемещаю в них значения 1 (или 2) очереди
            queueListFirst.addElement(queue.getFirstElement());
            queueListSecond.addElement(queue.getFirstElement());
            queue.removeFirstElement();
        }
    }

    private static MyLinkedListQueue<Integer> formResultQueue(MyLinkedListQueue<Integer> queueFirst,
                                                              MyLinkedListQueue<Integer> queueSecond) throws Exception {
        int count = queueSecond.count();
        for (int i = 0; i < count; i++) {
            queueFirst.addElement(queueSecond.getFirstElement());
            queueSecond.removeFirstElement();
        }

        return queueFirst;
    }

    private static void printListQueue(MyLinkedListQueue<Integer> listQueue, int numberQueue) throws Exception {
        if (numberQueue == 1) {
            System.out.print("Первая очередь: ");
        } else {
            System.out.print("Вторая очередь: ");
        }

        int count = listQueue.count();
        for (int i = 0; i < count - 1; i++) {
            System.out.print(listQueue.getFirstElement() + ", ");
            listQueue.removeFirstElement();
        }
        System.out.println(listQueue.getFirstElement());
    }
}
