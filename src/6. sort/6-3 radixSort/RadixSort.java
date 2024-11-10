



public class RadixSort {

    // Hàm để thực hiện Counting Sort dựa trên chữ số tại exp (1, 10, 100, ...)
    public static void countingSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // Mảng xuất kết quả
        int[] count = new int[10];
        // Khởi tạo mảng đếm
        for (int i = 0; i < 10; i++)
            count[i] = 0;

        // Đếm số lần xuất hiện của từng chữ số
        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Thay đổi count[i] để nó chứa vị trí cuối cùng của chữ số i trong mảng xuất
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Xây dựng mảng xuất kết quả bằng cách đi ngược từ cuối mảng để giữ tính ổn định
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Sao chép mảng xuất kết quả vào mảng ban đầu
        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // Hàm để tìm phần tử lớn nhất trong mảng
    public static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    // Hàm thực hiện Radix Sort
    public static void radixSort(int[] arr, int n) {
        // Tìm phần tử lớn nhất để biết số chữ số
        int m = getMax(arr, n);

        // Thực hiện Counting Sort cho từng chữ số
        // exp là 10^i nơi i là số chữ số hiện tại
        for (int exp = 1; m / exp > 0; exp *= 10)
            countingSort(arr, n, exp);
    }

    // Hàm để in mảng
    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Phương thức chính để chạy ví dụ
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;

        System.out.println("Mảng ban đầu:");
        printArray(arr, n);

        radixSort(arr, n);

        System.out.println("Mảng sau khi sắp xếp (Radix Sort):");
        printArray(arr, n);
    }
}
