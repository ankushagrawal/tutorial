package com.ankush.tutorial.linklist;

public class LinkedList {

    public Node head;

    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void printList() {
        System.out.println("printing list");
        Node n = head;
        while (n != null) {
            System.out.print(n.data+ " ");
            n = n.next;
        }
        System.out.println();
        System.out.println("done printing");
    }

    public void insertAtStart(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    public void insertAfter(Node prevNode, int data) {
        Node node = new Node(data);
        node.next = prevNode.next;
        prevNode.next = node;

    }

    public void insertAtLast(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;

    }

    public void deleteNodeAtPosition(int pos) {
        if (pos == 1) {
            if (head.next == null) {
                head = null;
            } else {
                head = head.next;
            }
            return;
        }
        Node n = head;
        int counter = 1;
        Node prev = head;
        while (n != null) {

            if (counter == pos) {
                prev.next = n.next;
                return;
            }
            prev = n;
            n = n.next;
            counter++;

        }
    }

    public int length() {
        int length = 0;
        Node n = head;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
    }

    public int recursiveLength() {
        return recursiveLength(head);
    }

    public int recursiveLength(Node node) {
        if (node == null) return 0;
        return 1+recursiveLength(node.next);
    }


    public int countLoopLength() {
        int loopLength = 1;
        Node firstP = head;
        Node secondP = head;
        while (firstP != null && secondP != null && secondP.next != null) {
            firstP = firstP.next;
            secondP = secondP.next.next;
            if (firstP == secondP) {
                Node curr = firstP;
                Node next = curr.next;
                while (next != curr) {
                    loopLength++;
                    next = next.next;
                }
                return loopLength;
            }
        }
        return 0;
    }

    public boolean hasLoop() {
        Node firstP = head;
        Node secondP = head;
        while (firstP != null && secondP != null && secondP.next != null) {
            firstP = firstP.next;
            secondP = secondP.next.next;
            if (firstP == secondP) return true;
        }
        return false;
    }

    public void deleteDuplicateFromSortedIncreasingLinkedList() {
        Node n = head;
        Node nextNext;
        while (n.next != null) {
            if (n.data == n.next.data) {
                nextNext = n.next.next;
                n.next = nextNext;
            } else {
                n = n.next;
            }

        }
    }

    public static LinkedList intersectionOfTwoSortedList(LinkedList linkedList1, LinkedList linkedList2) {
        LinkedList linkedList = new LinkedList();
        Node node1 = linkedList1.head;
        Node node2 = linkedList2.head;
        while (node1 != null && node2 != null) {
            if (node1.data == node2.data) {
                linkedList.insertAtLast(node1.data);
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1.data > node2.data) {
                node2 = node2.next;
            } else {
                node1 = node1.next;
            }
        }
        return linkedList;

    }

    private static void validateQuickSort() {
        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtLast(11);
        linkedList1.insertAtLast(20);
        linkedList1.insertAtLast(13);
        linkedList1.insertAtLast(40);
        linkedList1.insertAtLast(1);
        linkedList1.printList();
        linkedList1.quickSort(linkedList1);
        linkedList1.printList();
    }

    private static void validateSwap() {
        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtLast(11);
        linkedList1.insertAtLast(20);
        linkedList1.insertAtLast(13);
        linkedList1.insertAtLast(40);
        linkedList1.insertAtLast(1);
        linkedList1.printList();
        linkedList1.swap(linkedList1, 2, 3);
        System.out.println(linkedList1.hasLoop());
        System.out.println("length = "+linkedList1.countLoopLength());
        linkedList1.printList();
    }
    public static void main(String[] args) {
        validateSwap();
//        validateQuickSort();
//        validateIntersection();
//        validateDeletingDuplicate();
//        validateBasicLinkedListFunctions();
//        validateLoop();

    }

    private static void validateIntersection() {
        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtLast(1);
        linkedList1.insertAtLast(2);
        linkedList1.insertAtLast(3);
        linkedList1.insertAtLast(4);
        linkedList1.insertAtLast(6);
        linkedList1.printList();

        LinkedList linkedList2 = new LinkedList();
        linkedList2.insertAtLast(2);
        linkedList2.insertAtLast(4);
        linkedList2.insertAtLast(6);
        linkedList2.insertAtLast(8);
        linkedList2.printList();
        LinkedList linkedList = intersectionOfTwoSortedList(linkedList1, linkedList2);
        linkedList.printList();
    }
    private static void validateDeletingDuplicate() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtLast(11);
        linkedList.insertAtLast(11);
        linkedList.insertAtLast(11);
        linkedList.insertAtLast(21);
        linkedList.insertAtLast(43);
        linkedList.insertAtLast(43);
        linkedList.insertAtLast(60);
        linkedList.printList();
        linkedList.deleteDuplicateFromSortedIncreasingLinkedList();
        linkedList.printList();
    }

    private static void validateBasicLinkedListFunctions() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtLast(5);
        linkedList.printList();
        linkedList.insertAtLast(3);
        linkedList.printList();
        linkedList.insertAtLast(1);
        linkedList.printList();
        linkedList.insertAtStart(6);
        linkedList.printList();
        System.out.println("length = "+linkedList.length());
        System.out.println("recursive length = "+linkedList.recursiveLength());
        linkedList.insertAfter(linkedList.head.next,4);
        linkedList.printList();
        linkedList.deleteNodeAtPosition(3);
        linkedList.printList();
        linkedList.deleteNodeAtPosition(1);
        linkedList.printList();
        linkedList.deleteNodeAtPosition(3);
        linkedList.printList();
        linkedList.deleteNodeAtPosition(1);
        linkedList.printList();
        linkedList.deleteNodeAtPosition(1);
        linkedList.printList();
    }

    private void quickSort(LinkedList linkedList) {
        int length = linkedList.length();

        quickSort(linkedList, 0, length -1);
    }

    private void quickSort(LinkedList linkedList, int low, int high) {
        if (low < high) {
            int p = partition(linkedList, low, high);
            quickSort(linkedList, low, p-1);
            quickSort(linkedList, p+1, high);
        }

    }

    private int getAtIndex(LinkedList linkedList, int index) {
        int currIndex = 0;
        Node n = linkedList.head;
        while (n != null) {
            if (currIndex == index) return n.data;
            currIndex++;
            n = n.next;
        }
        return 0;
    }

    private Node getNodeAtIndex(LinkedList linkedList, int index) {
        int currIndex = 0;
        Node n = linkedList.head;
        while (n != null) {
            if (currIndex == index) return n;
            currIndex++;
            n = n.next;
        }
        return null;
    }

    private void swap(LinkedList linkedList, int i, int j) {
        // a->b->c->d->e->f
        //swap b and e
        // a->e->c->d->b->f
        //now swap a and d
        // d->e->c->a->b->f
        System.out.println("i ="+i+", j = "+j);
        Node prevI = getPreviousNode(linkedList, i);

        Node prevJ = getPreviousNode(linkedList, j);
        Node nodeI = getNodeAtIndex(linkedList, i);
        Node nodeJ = getNodeAtIndex(linkedList, j);

        if (prevI != null) {
            prevI.next = nodeJ;
        } else {
            head = nodeJ;
        }

        if (prevJ != null) {
            prevJ.next = nodeI;
        } else {
            head = nodeI;
        }

        //11->20->13->40->1
        //swap 13 and 40
        //prevI = 20, prevJ = 13
        //nodeI = 13, nodeJ = 40

        // Swap next pointers
        Node temp = nodeI.next;
        nodeI.next = nodeJ.next;
        nodeJ.next = temp;
//
//        if (prevI == null) {
//            linkedList.head = nodeJ;
//            Node temp = nodeI.next;
//            nodeI.next = nodeJ.next;
//            prevJ.next = nodeI;
//            nodeJ.next = temp;
//        } else if(j-i >1) {
//            //11->20->13->40->1
//            //swap 20 and 40
//            //prevI = 11, prevJ = 13
//            //nodeI = 20, nodeJ = 40
//            prevI.next = nodeJ; //11->40
//            Node temp = nodeJ.next;//temp = 1
//            nodeJ.next = null;
//            nodeJ.next = nodeI.next;//40->13
//            prevJ.next = nodeI;//13->20
//            nodeI.next = null;
//            nodeI.next = temp;//20->1
//            //11->40->13->20->1
//
//        } else {
//            //11->20->13->40->1
//            //swap 13 and 40
//            //prevI = 20, prevJ = 13
//            //nodeI = 13, nodeJ = 40
//            prevI.next = nodeJ; //20->40
//            Node temp = nodeJ.next;//temp =1
//            nodeJ.next = nodeI;//40->13
//            nodeI.next = temp;//13->1
//            //11->20->40->13->1
//        }


    }

    private Node getPreviousNode(LinkedList linkedList, int i) {
        Node n = linkedList.head;
        int counter = 0;
        Node prev = null;
        while (n != null) {
            if (counter == i) {
                return prev;
            }
            prev = n;
            n = n.next;
            counter++;
        }
        return prev;
    }

    private int partition(LinkedList linkedList, int low, int high) {
        int i = low-1;
        int pivot = getAtIndex(linkedList, high);

        for (int j = low; j < high; j++) {
            if (getAtIndex(linkedList,j) <= pivot) {
                i++;
                swap(linkedList, i, j);
            }
        }
        swap(linkedList, i+1, high);
        return i+1;
    }

    private static void validateLoop() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtLast(20);
        linkedList.insertAtLast(4);
        linkedList.insertAtLast(15);
        linkedList.insertAtLast(10);
        linkedList.printList();
        System.out.println("has loop = "+linkedList.hasLoop());
        linkedList.head.next.next.next.next = linkedList.head;
        System.out.println("has loop = "+linkedList.hasLoop());
        System.out.println("loop length= "+linkedList.countLoopLength());
    }
}
