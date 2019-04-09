package com.ankush.tutorial.graph;

public class GraphMain {
    public static void main(String[] args) {
//        Graph undirectedGraph = new Graph(3,Graph.GraphType.UNDIRECTED);
//        undirectedGraph.addEdge(undirectedGraph, 0, 1);
//        undirectedGraph.addEdge(undirectedGraph, 0, 2);
////        graph.addEdge(graph, 2, 0);
//        undirectedGraph.printGraph(undirectedGraph);

        Graph directedGraph = new Graph(3,Graph.GraphType.DIRECTED);
        directedGraph.addEdge(directedGraph, 0, 1);
        directedGraph.addEdge(directedGraph, 1, 2);
        directedGraph.addEdge(directedGraph, 2, 0);
//        directedGraph.addEdge(directedGraph, 2, 0);
//        directedGraph.addEdge(directedGraph, 2, 1);
//        directedGraph.addEdge(directedGraph, 3, 4);
//        directedGraph.addEdge(directedGraph, 3, 4);
//        directedGraph.addEdge(directedGraph, 3, 5);
//        directedGraph.addEdge(directedGraph, 4, 5);
        directedGraph.printGraph(directedGraph);
//        directedGraph.traverseBFS(directedGraph, 0);
//        directedGraph.traverseDFS(directedGraph, 0);
//        directedGraph.traverseDFSUsingStack(directedGraph, 0);
        directedGraph.getSSC(directedGraph);
//        directedGraph.printGraph(directedGraph.reverseGraph(directedGraph));
        System.out.println("mother node : " + directedGraph.findMotherNode(directedGraph));
        int source = 2;
        int des = 1;
        System.out.println("has path from source : " + source  + " to destination : "+ des + " : "+
                directedGraph.hasPath(directedGraph, source, des));
        directedGraph.getTransitiveClosure(directedGraph);
    }
}
