/**
 * @Combination_Recursive (Liệt kê tổ hợp)
 * Giả sử chúng ta muốn liệt kê tất cả các tổ hợp của k phần tử
 *  từ một tập hợp có n phần tử.
 * 
 * @NOTE: Sử dụng đệ quy
 * 
 * @INPUT: {1, 2, 3, 4}
 * @OUTPUT: 1 2 
            1 3 
            1 4 
            2 3 
            2 4 
            3 4 

 */

public class CombinationsRecursive {

    public static void combine(int[] arr, int start, int k, String current) {
        if (k == 0) {
            System.out.println(current);
            return;
        }
        for (int i = start; i <= arr.length - k; i++) 
            combine(arr, i + 1, k - 1, current + arr[i] + " ");
        
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 2;
        combine(arr, 0, k, "");
    }
}
