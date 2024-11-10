### Mô tả phép quay trong cây AVL? Làm thế nào để sử dụng chúng để chèn/xóa một nút trong cây AVL?


- Cây AVL (Adelson-Velsky và Landis) là một loại cây tìm kiếm nhị phân tự cân bằng. Đặc 
điểm chính của cây AVL là:
    + Cân bằng cao: Đối với mỗi nút, độ chênh lệch chiều cao giữa cây con bên trái và cây con bên phải không vượt quá 1.
    + Thao tác tìm kiếm, chèn, xóa: Có thời gian thực hiện tốt nhất là O(log n) nhờ vào việc duy trì cân bằng.

- Trong cây AVL, có bốn loại phép quay chính được sử dụng để cân bằng cây:
    + Quay phải đơn (Right Rotation)
    + Quay trái đơn (Left Rotation)
    + Quay trái-phải kép (Left-Right Rotation)
    + Quay phải-trái kép (Right-Left Rotation)