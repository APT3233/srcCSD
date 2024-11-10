/**
 * @Brute-Force
 * Brute-Force để tìm kiếm pattern trong text 
 */



class BruteForceStringMatch {

    /**
     * Hàm tìm kiếm pattern trong text bằng thuật toán Brute-Force.
     *
     * @param text    Chuỗi lớn nơi cần tìm kiếm.
     * @param pattern Chuỗi con cần tìm kiếm.
     * @return Vị trí bắt đầu của pattern trong text, hoặc -1 nếu không tìm thấy.
     */
    public static int bruteForceSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Nếu độ dài pattern lớn hơn text, không thể tìm thấy.
        if (m > n) {
            return -1;
        }

        // Duyệt qua tất cả các vị trí có thể trong text.
        for (int i = 0; i <= n - m; i++) {
            int j;

            // So khớp từng ký tự của pattern với text.
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break; // Ký tự không khớp, di chuyển sang vị trí tiếp theo.
                }
            }

            // Nếu tất cả các ký tự đều khớp, trả về vị trí i.
            if (j == m) {
                return i;
            }
        }

        // Không tìm thấy pattern trong text.
        return -1;
    }

    // Phương thức chính để chạy ví dụ.
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        int position = bruteForceSearch(text, pattern);

        if (position != -1) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }
}
