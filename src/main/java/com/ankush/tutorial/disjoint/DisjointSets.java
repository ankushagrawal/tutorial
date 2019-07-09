package com.ankush.tutorial.disjoint;

public class DisjointSets {

    int[] parent;
    int[] rank;
    int n;

    public DisjointSets(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.n = n;
        init();
    }

    public void init() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            int result = find(parent[x]);
            parent[x] = result;
            return result;
        }
    }

    public void union(int x1, int x2) {
        int x1Parent = find(x1);
        int x2Parent = find(x2);

        if (x1Parent == x2Parent) return;

        if (rank[x1Parent] >= rank[x2Parent]) {
            parent[x2Parent] = x1Parent;
            rank[x1Parent] += rank[x2Parent];
        } else {
            parent[x1Parent] = x2Parent;
            rank[x2Parent] += rank[x1Parent];
        }
    }

    public static void main(String[] args) {
        DisjointSets sets = new DisjointSets(5);
        sets.union(0,2);
        sets.union(4,2);
        sets.union(3,1);

        display(sets.parent);
        display(sets.rank);

        if(sets.find(4) == sets.find(0)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        if(sets.find(1) == sets.find(0)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void display(int[] arr) {
        System.out.println();
        for(int i = 0; i<arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }
}
