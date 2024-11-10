# Binary Search Tree

Định nghĩa: BST là một cây nhị phân trong đó mỗi nút có tối đa hai con: con trái và con phải. Đối với mỗi nút:

    Các giá trị trong cây con bên trái nhỏ hơn giá trị của nút đó.

    Các giá trị trong cây con bên phải lớn hơn hoặc bằng giá trị của nút đó.

Các thao tác cơ bản:

    Tìm kiếm: Tìm một giá trị trong BST.

    Chèn: Thêm một giá trị mới vào BST.

    Xóa: Loại bỏ một nút có giá trị xác định khỏi BST.


## Folder 1 <code><small>4-1deleteNode</small></code>:

### 1. Xóa Một Nút Trong Cây Tìm Kiếm Nhị Phân (BST) Bằng Hai Phương Pháp: Sao Chép và Hợp Nhất

Phương Pháp Xóa Nút Trong BST

Khi xóa một nút trong BST, có ba trường hợp chính cần xem xét dựa trên số lượng con của nút cần xóa:

    Nút cần xóa không có con (lá): Chỉ cần loại bỏ nút đó.
    Nút cần xóa có một con: Thay thế nút đó bằng con duy nhất của nó.
    Nút cần xóa có hai con: Cần phải thực hiện thêm các bước phức tạp hơn để duy trì tính chất của BST.

1.1 Phương Pháp Sao Chép <code>Replacement</code>

Phương pháp sao chép liên quan đến việc tìm kiếm một nút thay thế cho nút cần xóa sao cho cây vẫn duy trì được tính chất của BST. Thông thường, có hai lựa chọn chính:

    Tìm phần tử lớn nhất trong cây con trái (predecessor).
    Tìm phần tử nhỏ nhất trong cây con phải (successor).

Các bước thực hiện:

    Xác định nút cần xóa (node_to_delete).

    Tìm successor hoặc predecessor của node_to_delete:
        Successor: Là nút có giá trị nhỏ nhất trong cây con phải của node_to_delete.
        Predecessor: Là nút có giá trị lớn nhất trong cây con trái của node_to_delete.

    Thay thế giá trị của node_to_delete bằng giá trị của successor hoặc predecessor.

    Xóa nút successor hoặc predecessor (nếu sử dụng predecessor thì tương tự). Vì successor hoặc predecessor sẽ có tối đa một con, việc xóa chúng sẽ đơn giản hơn.