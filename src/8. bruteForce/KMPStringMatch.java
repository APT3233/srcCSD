/**
 * @Brute-force
 * Thuật toán KMP để tìm kiếm pattern trong text 
 */


class KMPStringMatch {

    /**
     * Hàm xây dựng bảng lps (Longest Prefix Suffix).
     *
     * @param pattern Chuỗi pattern cần tiền xử lý.
     * @return Mảng lps.
     */
    public static int[] buildLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        lps[0] = 0; // LPS của ký tự đầu tiên luôn là 0.

        int len = 0; // Độ dài của tiền tố cũng là hậu tố hiện tại.
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // Không tăng i ở đây.
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    /**
     * Hàm tìm kiếm pattern trong text bằng thuật toán KMP.
     *
     * @param text    Chuỗi lớn nơi cần tìm kiếm.
     * @param pattern Chuỗi con cần tìm kiếm.
     * @return Vị trí bắt đầu của pattern trong text, hoặc -1 nếu không tìm thấy.
     */
    public static int KMPsearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) return 0; // Trường hợp pattern rỗng.

        int[] lps = buildLPSArray(pattern);

        int i = 0; // Chỉ số cho text.
        int j = 0; // Chỉ số cho pattern.

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                return i - j; // Tìm thấy pattern tại vị trí (i - j).
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Di chuyển pattern dựa trên lps.
                } else {
                    i++;
                }
            }
        }

        return -1; // Không tìm thấy pattern trong text.
    }

    // Phương thức chính để chạy ví dụ.
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        int position = KMPsearch(text, pattern);

        if (position != -1) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }
}
