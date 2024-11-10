/**
 * @SortestPath có trọng số ko âm
 * @method: dijkstra algo
 */

 import java.util.ArrayList;
 import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 import java.util.PriorityQueue;
 
 // Lớp đại diện cho một cạnh trong đồ thị
 class Edge {
     int to;
     int weight;
 
     Edge(int to, int weight) {
         this.to = to;
         this.weight = weight;
     }
 }
 
 // Lớp đại diện cho đồ thị sử dụng danh sách kề
 class Graph {
     private int numVertices;
     private List<List<Edge>> adjList;
 
     // Constructor
     public Graph(int numVertices) {
         this.numVertices = numVertices;
         adjList = new ArrayList<>(numVertices);
         for(int i = 0; i < numVertices; i++) {
             adjList.add(new ArrayList<>());
         }
     }
 
     // Phương thức thêm cạnh vào đồ thị (đồ thị vô hướng)
     public void addEdge(int from, int to, int weight) {
         adjList.get(from).add(new Edge(to, weight));
         adjList.get(to).add(new Edge(from, weight));
     }
 
     // Getter cho danh sách kề
     public List<List<Edge>> getAdjList() {
         return adjList;
     }
 
     // Getter cho số lượng đỉnh
     public int getNumVertices() {
         return numVertices;
     }
 }
 
 public class AllShortestPathsDijkstra {
     private Graph graph;
     private int numVertices;
     private List<List<Edge>> adjList;
     private int[][] distances;
     private List<Integer>[][] predecessors;
 
     @SuppressWarnings("unchecked")
     public AllShortestPathsDijkstra(Graph graph){
         this.graph = graph;
         this.numVertices = graph.getNumVertices();
         this.adjList = graph.getAdjList();
         distances = new int[numVertices][numVertices];
         predecessors = new ArrayList[numVertices][numVertices];
         for(int i = 0; i < numVertices; i++) {
             for(int j = 0; j < numVertices; j++) {
                 distances[i][j] = Integer.MAX_VALUE;
                 predecessors[i][j] = new ArrayList<>();
             }
         }
     }
 
     // Hàm Dijkstra mở rộng để lưu predecessors
     private void dijkstra(int src){
         PriorityQueue<Vertex> pq = new PriorityQueue<>();
         distances[src][src] = 0;
         pq.add(new Vertex(src, 0));
 
         while(!pq.isEmpty()){
             Vertex current = pq.poll();
             int u = current.vertex;
 
             for(Edge edge : adjList.get(u)){
                 int v = edge.to;
                 int weight = edge.weight;
 
                 if(distances[src][u] + weight < distances[src][v]){
                     distances[src][v] = distances[src][u] + weight;
                     predecessors[src][v].clear();
                     predecessors[src][v].add(u);
                     pq.add(new Vertex(v, distances[src][v]));
                 }
                 else if(distances[src][u] + weight == distances[src][v]){
                     predecessors[src][v].add(u);
                 }
             }
         }
     }
 
     // Hàm chạy Dijkstra từ tất cả các đỉnh
     public void computeAllShortestPaths(){
         for(int i = 0; i < numVertices; i++){
             dijkstra(i);
         }
     }
 
     // Hàm xây dựng tất cả các đường đi từ src đến dest
     public List<List<Integer>> getAllPaths(int src, int dest){
         List<List<Integer>> allPaths = new ArrayList<>();
         List<Integer> path = new ArrayList<>();
         path.add(dest);
         buildPaths(src, dest, path, allPaths);
         return allPaths;
     }
 
     private void buildPaths(int src, int current, List<Integer> path, List<List<Integer>> allPaths){
         if(current == src){
             List<Integer> validPath = new ArrayList<>(path);
             Collections.reverse(validPath);
             allPaths.add(validPath);
             return;
         }
 
         for(int pred : predecessors[src][current]){
             path.add(pred);
             buildPaths(src, pred, path, allPaths);
             path.remove(path.size() - 1);
         }
     }
 
     // Hàm in tất cả các đường đi ngắn nhất từ src đến dest
     public void printAllPaths(int src, int dest){
         List<List<Integer>> paths = getAllPaths(src, dest);
         System.out.println("Tất cả các đường đi ngắn nhất từ " + src + " đến " + dest + ":");
         for(List<Integer> path : paths){
             System.out.println(path);
         }
     }
 
     // Lớp Vertex để sử dụng trong PriorityQueue
     class Vertex implements Comparable<Vertex>{
         int vertex;
         int distance;
 
         Vertex(int vertex, int distance){
             this.vertex = vertex;
             this.distance = distance;
         }
 
         @Override
         public int compareTo(Vertex other){
             return Integer.compare(this.distance, other.distance);
         }
     }
 
     // Hàm chính để chạy ví dụ
     public static void main(String[] args){
         /*
             Ví dụ Đồ thị có trọng số:
                 (0)
                /   \
              10     5
              /       \
             (1)---1---(2)
              \     \
               1     2
                \     \
                (4)---1---(3)
         */
         Graph graph = new Graph(5);
         graph.addEdge(0, 1, 10);
         graph.addEdge(0, 2, 5);
         graph.addEdge(1, 2, 1);
         graph.addEdge(1, 4, 1);
         graph.addEdge(2, 4, 2);
         graph.addEdge(4, 3, 1);
         graph.addEdge(2, 3, 3);
 
         AllShortestPathsDijkstra asp = new AllShortestPathsDijkstra(graph);
         asp.computeAllShortestPaths();
 
         int src = 0;
         int dest = 3;
         asp.printAllPaths(src, dest);
         /*
             Kết quả:
             Tất cả các đường đi ngắn nhất từ 0 đến 3:
             [0, 2, 4, 3]
         */
     }
 }
 