## MST

**Cây Khung Tối Thiểu (MST)** của một đồ thị có trọng số liên thông là một cây khung (spanning tree) mà tổng trọng số các cạnh của nó là nhỏ nhất. MST bao gồm tất cả các đỉnh của đồ thị nhưng chỉ có số cạnh bằng <code>V−1</code>, trong đó <code>V</code> là số đỉnh của đồ thị.

- Thuật toán 1 <code>Kruskal</code>
Thuật toán <code>Kruskal</code> là một thuật toán dựa trên nguyên tắc chia để trị (greedy approach), trong đó ta chọn các cạnh có trọng số nhỏ nhất và thêm chúng vào cây khung nếu không tạo thành chu trình. Thuật toán sử dụng cấu trúc Union-Find (Disjoint Set) để kiểm tra và hợp nhất các tập hợp đỉnh.

- Thuật toán 2 <code>Prim</code> cũng là một thuật toán dựa trên nguyên tắc greedy, tương tự như Kruskal, nhưng thay vì chọn cạnh theo thứ tự tăng dần, Prim bắt đầu từ một đỉnh và mở rộng MST bằng cách chọn cạnh nhỏ nhất nối một đỉnh đã thuộc MST với đỉnh chưa thuộc MST. Thuật toán thường được triển khai hiệu quả bằng cách sử dụng Priority Queue (Heap). 