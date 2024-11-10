

- Tìm kiếm theo Chiều sâu (DFS - Depth-First Search)
<br>
 DFS là một thuật toán duyệt đồ thị bắt đầu từ một nút gốc và đi sâu vào các nút con trước khi quay trở lại và tiếp tục với các nút khác. DFS thường được sử dụng để:
    + Kiểm tra tính kết nối của đồ thị.
    + Tìm các thành phần liên thông.
    + Phát hiện chu trình trong đồ thị.
- Tìm kiếm theo Chiều rộng (BFS - Breadth-First Search)
<br>
BFS là một thuật toán duyệt đồ thị bắt đầu từ một nút gốc và duyệt theo từng lớp lân cận trước khi chuyển sang lớp tiếp theo. BFS thường được sử dụng để:
    + Tìm đường đi ngắn nhất trong đồ thị không trọng số.
    + Tìm cây bao trùm (Spanning Tree).
    + Phát hiện tính kết nối của đồ thị.

### 5-1 
1.  Sử dụng DFS để Kiểm Tra Khả Năng Kết Nối <br>
1.1 Ý tưởng:
    - Để kiểm tra xem một đồ thị có phải là đồ thị liên thông (connected) hay không ta có thể:
        + Bắt đầu từ một nút bất kỳ và thực hiện DFS.
        + Sau khi DFS hoàn thành, kiểm tra xem tất cả các nút có được duyệt hay không.
        Nếu tất cả các nút đều được duyệt, đồ thị liên thông.
        Ngược lại, đồ thị không liên thông.

2. Sử dụng BFS để Tìm Cây Bao Trùm <br>
2.1 Ý tưởng: 
    - Cây bao trùm (Spanning Tree) của một đồ thị là một cây mà nó bao gồm tất cả các nút của đồ thị và là cây, tức là không có chu trình. BFS có thể được sử dụng để xây dựng cây bao trùm bằng cách:
    + Bắt đầu từ một nút gốc.
    + Duyệt theo BFS và ghi lại các cạnh được sử dụng để duyệt, tạo thành cây bao trùm.


## 5-2 colorMap
**Bài toán tô màu bản đồ** là một trong những bài toán cổ điển trong lý thuyết đồ thị và khoa học máy tính. Mục tiêu của bài toán này là **gán màu cho các vùng trên bản đồ** sao cho:

- **Không có hai vùng liền kề** nào (có chung một cạnh) **cùng màu**.
- **Sử dụng số lượng màu tối thiểu**.


### 1. <code>Greedy Algorithm</code>
Thuạt toán tham lam là một phương pháp đơn giản và hiệu quả để giải bài toán tô màu bản đồ. Ý tưởng cơ bản của thuật toán này là **gán màu cho các vùng một cách tuần tự**, mỗi lần chọn màu nhỏ nhất khả dụng mà không vi phạm điều kiện không có hai vùng liền kề cùng màu.

####  Các Bước Thực Hiện

1. **Xác định thứ tự tô màu các vùng:**
   - Sắp xếp các vùng theo một trật tự nhất định, thường là theo **độ bậc giảm dần** (vùng có nhiều vùng liền kề nhất được tô trước). Phương pháp này được biết đến với tên gọi **Welsh-Powell Algorithm**.

2. **Gán màu cho từng vùng theo thứ tự:**
   - Bắt đầu từ vùng đầu tiên trong danh sách đã sắp xếp.
   - Gán màu nhỏ nhất khả dụng mà không vi phạm điều kiện không có hai vùng liền kề cùng màu.
   - Lặp lại quá trình này cho đến khi tất cả các vùng được gán màu.

##### Ví Dụ

Giả sử chúng ta có một bản đồ với 4 vùng: A, B, C, D, với các vùng liền kề như sau:

- A liền kề với B và C.
- B liền kề với A và D.
- C liền kề với A và D.
- D liền kề với B và C.

**Thứ tự sắp xếp theo độ bậc giảm dần:** A, B, C, D.

**Quá trình gán màu:**
1. **Vùng A:** Gán màu 1.
2. **Vùng B:** Liền kề với A (màu 1), nên gán màu 2.
3. **Vùng C:** Liền kề với A (màu 1), nên gán màu 2.
4. **Vùng D:** Liền kề với B và C (cả hai đều màu 2), nên gán màu 1.

**Kết quả:** Sử dụng 2 màu (1 và 2).


### <code>2. Backtracking Algo</code>
Thuật toán quay lui là một phương pháp tìm kiếm toàn diện, kiểm tra tất cả các khả năng để tìm ra số màu tối thiểu cần thiết. 
- Trong ngữ cảnh tô màu bản đồ, thuật toán này sẽ:
    + Gán màu cho từng vùng một cách tuần tự.
    + Kiểm tra tính hợp lệ của màu gán: Đảm bảo không vi phạm điều kiện không hai vùng liền kề cùng màu.
    + Nếu gặp vi phạm, quay lui và thử màu khác.
    + Lặp lại quá trình cho đến khi tất cả các vùng được gán màu hợp lệ.


**Kết luận: Tô màu bản đồ với số màu tối thiểu là một bài toán quan trọng trong lý thuyết đồ thị với nhiều ứng dụng thực tiễn. Thuật toán tham lam là lựa chọn phù hợp khi cần giải nhanh và không yêu cầu số màu tối ưu, trong khi thuật toán quay lui thích hợp cho các bài toán yêu cầu tìm ra số màu tối thiểu nhưng lại không phù hợp với đồ thị lớn do độ phức tạp thời gian cao.**


## 5-3 <code>ShortestPath</code>

- Thuật toán Dijkstra là một trong những thuật toán hiệu quả nhất để tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh còn lại trong một đồ thị có trọng số không âm.

- 2 cách tìm đường đi ngắn nhất giữa mọi cặp đỉnh trong đồ thị phụ thuộc vào loại đồ thị và yêu cầu cụ thể của bài toán:
    + Đồ thị không trọng số: Sử dụng BFS từ mỗi đỉnh nguồn để tìm và xây dựng tất cả các đường đi ngắn nhất.
    + Đồ thị có trọng số không âm: Sử dụng Thuật toán Dijkstra mở rộng từ mỗi đỉnh nguồn để lưu trữ và xây dựng tất cả các đường đi ngắn nhất.

## 5-4 <code>MST</code>

**Cây Khung Tối Thiểu (MST)** của một đồ thị có trọng số liên thông là một cây khung (spanning tree) mà tổng trọng số các cạnh của nó là nhỏ nhất. MST bao gồm tất cả các đỉnh của đồ thị nhưng chỉ có số cạnh bằng <code>V−1</code>, trong đó <code>V</code> là số đỉnh của đồ thị.

- Thuật toán 1 <code>Kruskal</code>
Thuật toán <code>Kruskal</code> là một thuật toán dựa trên nguyên tắc chia để trị (greedy approach), trong đó ta chọn các cạnh có trọng số nhỏ nhất và thêm chúng vào cây khung nếu không tạo thành chu trình. Thuật toán sử dụng cấu trúc Union-Find (Disjoint Set) để kiểm tra và hợp nhất các tập hợp đỉnh.

- Thuật toán 2 <code>Prim</code> cũng là một thuật toán dựa trên nguyên tắc greedy, tương tự như Kruskal, nhưng thay vì chọn cạnh theo thứ tự tăng dần, Prim bắt đầu từ một đỉnh và mở rộng MST bằng cách chọn cạnh nhỏ nhất nối một đỉnh đã thuộc MST với đỉnh chưa thuộc MST. Thuật toán thường được triển khai hiệu quả bằng cách sử dụng Priority Queue (Heap). 

## 5-5 <code>Euler</code>

**Chu trình Euler<code>Eulerian Cycle</code>** là một chu trình trong đồ thị đi qua tất cả các cạnh của đồ thị một cách chính xác (không đi qua cạnh nào nhiều hơn một lần) và quay trở lại đỉnh xuất phát.

**Đường đi Euler<code>Euler Path</code>** là một đường đi trong đồ thị đi qua tất cả các cạnh của đồ thị một cách chính xác nhưng không nhất thiết phải quay trở lại đỉnh xuất phát.

