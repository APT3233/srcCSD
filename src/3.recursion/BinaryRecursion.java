/**
 * @Binary_Recursion
 * Dùng đệ quy thường để giải bài toán tính giai thừa
 */


public class BinaryRecursion {

    public static int recursion(int n){
        if(n <= 1)
            return 1;

        return n * recursion(n-1);
    }

    public static void main(String[] args) {
        int a = recursion(5);
        System.out.println(a);
    }
}
