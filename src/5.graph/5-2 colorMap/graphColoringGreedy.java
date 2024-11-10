/**
 * @Sequential_Alg (Thuật toán tuần tự)
 * @method: Greedy Alg (Tham lam)
 * 
 */

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;
    
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Method to add an edge (undirected graph)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    // Getter for adjacency list
    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    // Getter for number of vertices
    public int getNumVertices() {
        return numVertices;
    }
}


class GraphColoringGreedy {
    private int numVertices;
    private List<List<Integer>> adjList;

    public GraphColoringGreedy(Graph graph) {
        this.numVertices = graph.getNumVertices();
        this.adjList = graph.getAdjList();
    }

    // Function to perform greedy coloring
    public int[] colorGraph() {
        int[] result = new int[numVertices];
        Arrays.fill(result, -1); // -1 indicates no color assigned

        // Assign the first color to the first vertex
        result[0] = 0;

        // Temporary array to store the available colors. False value indicates the color is available
        boolean[] available = new boolean[numVertices];
        Arrays.fill(available, false);

        // Assign colors to remaining vertices
        for (int u = 1; u < numVertices; u++) {
            // Process all adjacent vertices and flag their colors as unavailable
            for (int i : adjList.get(u)) {
                if (result[i] != -1) {
                    available[result[i]] = true;
                }
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < numVertices; cr++) {
                if (!available[cr]) {
                    break;
                }
            }

            // Assign the found color
            result[u] = cr;

            // Reset the values back to false for the next iteration
            Arrays.fill(available, false);
        }

        return result;
    }

    // Function to print the result
    public void printColoring(int[] result) {
        System.out.println("Vertex : Color");
        for (int u = 0; u < numVertices; u++) {
            System.out.println(u + " : " + result[u]);
        }
        int maxColor = Arrays.stream(result).max().getAsInt() + 1;
        System.out.println("Total colors used: " + maxColor);
    }

    // Main method for demonstration
    public static void main(String[] args) {
        /*
         Example Graph:
             0
            / \
           1---2
           \   |
             3
        */
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        GraphColoringGreedy gc = new GraphColoringGreedy(graph);
        int[] coloring = gc.colorGraph();
        gc.printColoring(coloring);
    }
}
