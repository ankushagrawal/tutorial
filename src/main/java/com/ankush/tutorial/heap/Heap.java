package com.ankush.tutorial.heap;

public class Heap {


    public void addToMaxHeap(int[] arr, int x, int elementsInMaxHeap) {
        arr[elementsInMaxHeap] = x;
        elementsInMaxHeap++;
        int startIndex = (elementsInMaxHeap/2) - 1;
        for (int i = startIndex; i >= 0; i--) {
            maxHeapify(arr, elementsInMaxHeap, i);
        }
    }

    public void addToMinHeap(int[] arr, int x, int elementsInMaxHeap) {
        arr[elementsInMaxHeap++] = x;
//        elementsInMaxHeap++;
        int startIndex = (elementsInMaxHeap/2) - 1;
        for (int i = startIndex; i >= 0; i--) {
            minHeapify(arr, elementsInMaxHeap, i);
        }
    }

    private void minHeapify(int[] arr, int n, int i) {
        int smallest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        if (l < n && arr[l] < arr[smallest])
            smallest = l;
        if (r < n && arr[r] < arr[smallest])
            smallest = r;

        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            // Recursively heapify the affected sub-tree
            maxHeapify(arr, n, smallest);
        }
    }

    private void maxHeapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            maxHeapify(arr, n, largest);
        }
    }

    private void printHeap(int arr[], int n) {
        System.out.println("Array representation of Heap is:");

        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public static void main(String[] args) {

        getTopElements(5);
//        Heap heap = new Heap();
//        //top 5 elements
//        int n = 7;
//        int[] arr = new int[n];
//        int[] minHeapArr = new int[n];
//        int currElements = 0;
//        heap.addToMinHeap(minHeapArr, 10, currElements);
//        heap.addToMaxHeap(arr, 10, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 20, currElements);
//        heap.addToMaxHeap(arr, 20, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 1, currElements);
//        heap.addToMaxHeap(arr, 1, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 12, currElements);
//        heap.addToMaxHeap(arr, 12, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 18, currElements);
//        heap.addToMaxHeap(arr, 18, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 30, currElements);
//        heap.addToMaxHeap(arr, 30, currElements++);
//
//        heap.addToMinHeap(minHeapArr, 5, currElements);
//        heap.addToMaxHeap(arr, 5, currElements++);
//
//        heap.printHeap(arr, currElements);
//        heap.printHeap(minHeapArr, currElements);
    }

    private static void getTopElements(int n) {
        Heap heap = new Heap();
        //top n elements
        int[] maxHeapArr = new int[n];
        int[] minHeapArr = new int[n];
        int currCount = 0;

        addToHeap(n, maxHeapArr, minHeapArr, currCount, heap);
//        heap.printHeap(maxHeapArr, n);
//        heap.printHeap(minHeapArr, n);
    }

    private static void addToHeap(int n, int[] maxHeapArr, int[] minHeapArr, int currCount, Heap heap) {
        int x = getElement(currCount);
        if (currCount < n) {

            heap.addToMinHeap(minHeapArr, x, currCount);
            heap.addToMaxHeap(maxHeapArr, x, currCount++);
            addToHeap(n, maxHeapArr, minHeapArr, currCount, heap);
        } else if (currCount < 20) {
            //check x with maxHeap
            currCount++;
            if (maxHeapArr[0] < x || minHeapArr[0] < x) {
                //remove min element from maxHeap
                int min = minHeapArr[0];
                removeFromMaxHeap(maxHeapArr, min);
                removeFromMinHeap(minHeapArr, min);
                heap.addToMinHeap(minHeapArr, x, n-1);
                heap.addToMaxHeap(maxHeapArr, x, n-1);
                heap.printHeap(maxHeapArr, n);
                heap.printHeap(minHeapArr, n);

            }
            addToHeap(n, maxHeapArr, minHeapArr, currCount, heap);
        }
    }

    private static void removeFromMinHeap(int[] minHeapArr, int x) {
        for (int i = 0; i < minHeapArr.length; i++) {
            if (minHeapArr[i] == x) {
                while (i < minHeapArr.length -1) {
                    minHeapArr[i] = minHeapArr[i+1];
                    i++;
                }
                break;
            }
        }
    }

    private static void removeFromMaxHeap(int[] maxHeapArr, int x) {
        for (int i = 0; i < maxHeapArr.length; i++) {
            if (maxHeapArr[i] == x) {
                while (i < maxHeapArr.length-1) {
                    maxHeapArr[i] = maxHeapArr[i+1];
                    i++;
                }
                break;
            }
        }
    }

    private static int getElement(int index) {
        int[] tempArr = new int[]{3,1,4,12,45,32,46,78,6,4,2,12,5,7,8,9,67,54,93,96,78,98,
                67,90,67,45,65,54,57,67,89,78,56,34,21,23,24,45};
        return tempArr[index];
    }
}
