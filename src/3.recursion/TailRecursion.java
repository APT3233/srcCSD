/**
 * @Tail_Recursion
 * Sử dụng đệ quy đuôi để giải bài toán giai thừa
 */

public class TailRecursion {
    
    public static int recursion(int n, int accmulator)
    {
        if(n <= 1)
            return accmulator;
        
        return recursion(n-1, n * accmulator);
    }

    public static void main(String[] args) {
        int res = recursion(5, 1);
        System.out.println(res);
    }
}

