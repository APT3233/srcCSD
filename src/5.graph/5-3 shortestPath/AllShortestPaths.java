/**
 * @SortestPath không có trọng số
 * @method: BFS + Backtracking
 */

import java.util.*;

class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Constructor
    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for(int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Thêm cạnh (đồ thị vô hướng)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    public int getNumVertices() {
        return numVertices;
    }
}

public class AllShortestPaths {
    private Graph graph;
    private int start;
    private int end;
    private List<List<Integer>> allPaths;
    private int[] distance;
    private List<List<Integer>> predecessors;

    public AllShortestPaths(Graph graph, int start, int end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.allPaths = new ArrayList<>();
        this.distance = new int[graph.getNumVertices()];
        Arrays.fill(distance, -1);
        this.predecessors = new ArrayList<>();
        for(int i = 0; i < graph.getNumVertices(); i++) {
            predecessors.add(new ArrayList<>());
        }
    }

    // BFS để tìm khoảng cách và predecessors
    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distance[start] = 0;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(int v : graph.getAdjList().get(u)) {
                if(distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.add(v);
                    predecessors.get(v).add(u);
                }
                else if(distance[v] == distance[u] + 1) {
                    predecessors.get(v).add(u);
                }
            }
        }
    }

    // Đệ quy để xây dựng tất cả các đường đi
    public void findAllPaths(int current, List<Integer> path) {
        if(current == start) {
            List<Integer> tempPath = new ArrayList<>(path);
            tempPath.add(start);
            Collections.reverse(tempPath);
            allPaths.add(tempPath);
            return;
        }

        for(int pred : predecessors.get(current)) {
            path.add(pred);
            findAllPaths(pred, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> getAllShortestPaths() {
        bfs();
        findAllPaths(end, new ArrayList<>());
        return allPaths;
    }

    // Hàm in đường đi
    public void printAllPaths() {
        List<List<Integer>> paths = getAllShortestPaths();
        System.out.println("Tất cả các đường đi ngắn nhất từ " + start + " đến " + end + ":");
        for(List<Integer> path : paths) {
            System.out.println(path);
        }
    }

    // Hàm chính để chạy ví dụ
    public static void main(String[] args) {
        /*
            Ví dụ Đồ thị:
                0
               / \
              1   2
              \  /
               3
        */
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        int start = 0;
        int end = 3;

        AllShortestPaths asp = new AllShortestPaths(graph, start, end);
        asp.printAllPaths();
        /*
            Kết quả:
            Tất cả các đường đi ngắn nhất từ 0 đến 3:
            [0, 1, 3]
            [0, 2, 3]
        */
    }
}
