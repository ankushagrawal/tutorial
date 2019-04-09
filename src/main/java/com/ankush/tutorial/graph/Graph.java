package com.ankush.tutorial.graph;

import java.util.*;

public class Graph {

    private int nodes;

    private GraphType type;

    private LinkedList<Integer> adjListArray[];

    public Graph(int nodes, GraphType type) {
        this.nodes = nodes;
        this.type = type;
        adjListArray = new LinkedList[nodes];

        for (int i = 0; i < nodes; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(Graph graph, int src, int dest) {
        graph.adjListArray[src].add(dest);
        if (!GraphType.DIRECTED.equals(graph.type)) {
            graph.adjListArray[dest].add(src);
        }
    }

    public void printGraph(Graph graph) {
        for (int node = 0; node < graph.nodes; node++) {
            System.out.println("for node : " + node + " path to ");
            for (Integer edge : graph.adjListArray[node]) {
                System.out.print(" --> " + edge + " " );
            }
            System.out.println();
        }
    }

    public void traverseBFS(Graph graph, int source) {
        System.out.println("BFS traversal");
        boolean isTraversed[] = new boolean[graph.nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            source = ((LinkedList<Integer>) queue).poll();
            if (!isTraversed[source]) {
                System.out.print(source + " ");
                isTraversed[source] = true;
            }

            LinkedList<Integer> edges = graph.adjListArray[source];
            for(Integer edgeNodes : edges) {
                queue.add(edgeNodes);
            }

        }
        System.out.println();
        System.out.println("..................");
    }

    public void traverseDFS(Graph graph, int source) {
        System.out.println("DFS traversal using recursion");
        boolean isTraversed[] = new boolean[graph.nodes];

        traverseDFS(graph, isTraversed, source);
        System.out.println();
        System.out.println("..................");
    }

    private void traverseDFS(Graph graph, boolean[] isTraversed, int source) {
        if (isTraversed[source]) {
            return;
        }
        System.out.print(source + " ");
        isTraversed[source] = true;
        LinkedList<Integer> edges = graph.adjListArray[source];
        for (Integer edge : edges) {
            traverseDFS(graph, isTraversed, edge);
        }
    }

    public int findMotherNode(Graph graph) {
        Stack<Integer> stack = createStackUsingDFSForSSC(graph);
        int source = stack.peek();
        //do DFS using source
        List<Integer> dfs = traverseDFSUsingStack(graph, source);
        if (dfs.size() == graph.nodes) {
            return source;
        }
        return -1;
    }

    public List<Integer> traverseDFSUsingStack(Graph graph, int source) {
        List<Integer> dfs = new ArrayList<>();
        System.out.println("DFS traversal using Stack, with source : " + source);
        boolean isVisited[] = new boolean[graph.nodes];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while(!stack.isEmpty()) {
            source = stack.pop();
            isVisited[source] = true;
            System.out.print(source + " ");
            dfs.add(source);
            LinkedList<Integer> edges = graph.adjListArray[source];
            for (Integer edge : edges) {
                if (!isVisited[edge]) {
                    stack.push(edge);
                }
            }
        }
        System.out.println();
        System.out.println("..................");
        return dfs;
    }


    //Kosaraju's algo
    public void getSSC(Graph graph) {
        Stack<Integer> stack = createStackUsingDFSForSSC(graph);
        System.out.println(stack);
        Graph reverseGraph = reverseGraph(graph);
        List<List<Integer>> ssc = traverseReverseGraphWithStackOrder(reverseGraph, stack);
        System.out.println(ssc);
    }

    private List<List<Integer>> traverseReverseGraphWithStackOrder(Graph reverseGraph, Stack<Integer> stack) {
        List<List<Integer>> output = new ArrayList<>();
        Stack<Integer> tempStack = new Stack<>();
        boolean isVisited[] = new boolean[reverseGraph.nodes];
        while(!stack.isEmpty()) {
            int source = stack.pop();
            if (!isVisited[source]) {
                List<Integer> ssc = new ArrayList<>();
                tempStack.push(source);
                while (!tempStack.isEmpty()) {
                    int currSource = tempStack.pop();
                    isVisited[currSource] = true;
                    ssc.add(currSource);
                    for (Integer edge : reverseGraph.adjListArray[currSource]) {
                        if (!isVisited[edge]) {
                            tempStack.push(edge);
                        }
                    }
                }
                output.add(ssc);
            }
        }

        return output;
    }

    public Graph reverseGraph(Graph graph) {
        Graph reverseGraph = new Graph(graph.nodes, graph.type);
        for (int i = 0; i < graph.nodes; i++) {
            LinkedList<Integer> edges = graph.adjListArray[i];
            for (Integer dest : edges) {
                reverseGraph.addEdge(reverseGraph, dest, i);
            }
        }
        return reverseGraph;
    }


    public Integer[][] getTransitiveClosure(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        Integer[][] transitiveClosure = new Integer[graph.nodes][graph.nodes];
        init(transitiveClosure);
        int source = 0;
        stack.push(source);
        boolean isVisited[] = new boolean[graph.nodes];
        while (!stack.isEmpty()) {
            source = stack.peek();
            boolean allChildsVisited = true;
            isVisited[source] = true;

            for (Integer connectedNode : graph.adjListArray[source]) {
                if (!isVisited[connectedNode]) {
                    transitiveClosure[source][connectedNode] = 1;
                    stack.push(connectedNode);
                    allChildsVisited = false;
                }
            }
            if (allChildsVisited) {
                source = stack.pop();
                for (Integer connectedNode : graph.adjListArray[source]) {
                    for (int col = 0 ; col < graph.nodes; col++) {
                        if (transitiveClosure[connectedNode][col] == 1) {
                            transitiveClosure[source][col] = 1;
                        }
                    }
                }
            }
        }

        display(transitiveClosure);
        return transitiveClosure;
    }

    private void display(Integer[][] transitiveClosure) {
        System.out.println("transitive closure");
        for (int i = 0; i < transitiveClosure.length; i++) {
            for (int j = 0; j < transitiveClosure.length; j++) {
                System.out.print(transitiveClosure[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    private void init(Integer[][] transitiveClosure) {
        for (int i = 0; i < transitiveClosure.length; i++) {
            for (int j = 0; j < transitiveClosure.length; j++) {
                transitiveClosure[i][j] = 0;
            }
        }
    }

    public boolean hasPath(Graph graph, int source, int destination) {
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        boolean isVisited[] = new boolean[graph.nodes];
        while (!stack.isEmpty()) {
            source = stack.pop();
            if (destination == source) {
                return true;
            }
            for (Integer nodes : graph.adjListArray[source]) {
                if (!isVisited[nodes]) {
                    stack.push(nodes);
                }
            }
        }
        return false;
    }

    private Stack<Integer> createStackUsingDFSForSSC(Graph graph) {
        boolean isVisited[] = new boolean[graph.nodes];
        int source = getRandomSource(isVisited);
        Stack<Integer> outputStack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();
        tempStack.push(source);
        while(!tempStack.isEmpty() || !allNodesVisited(isVisited)) {
            if (tempStack.isEmpty()) {
                source = getRandomSource(isVisited);
            } else {
                source = tempStack.peek();
            }
            isVisited[source] = true;
            LinkedList<Integer> edges = graph.adjListArray[source];
            boolean allEdgesTraversed = true;
            for (Integer edge : edges) {
                if (!isVisited[edge]) {
                    allEdgesTraversed = false;
                    tempStack.push(edge);
                }
            }
            if (allEdgesTraversed) {
                outputStack.push(source);
                if (!tempStack.isEmpty())
                    tempStack.pop();
            }
        }

        return outputStack;
    }

    private boolean allNodesVisited(boolean[] isVisited) {
        for(int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                return false;
            }
        }
        return true;
    }

    private int getRandomSource(boolean[] isVisited) {
        for (int i = 0; i < isVisited.length; i++) {
            if(!isVisited[i]) {
                return i;
            }
        }
        return 0;
    }

    public static enum GraphType{
        DIRECTED,
        UNDIRECTED;
    }
}
