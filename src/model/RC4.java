package model;

public class RC4 {
    private String plain_text; // Văn bản gốc
    private String key; // Khóa
    private int[] S; // Mảng trạng thái
    private int[] key_list; // Danh sách khóa
    private int[] pt; // Văn bản gốc dưới dạng số nguyên
    private int[] key_stream; // Luồng khóa
    private String cipherText; // Biến lưu kết quả mã hóa

    public String getPlain_text() {
        return plain_text;
    }

    public void setPlain_text(String plain_text) {
        this.plain_text = plain_text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(int key) {
       this.key = Integer.toBinaryString(key); // Chuyển đổi số nguyên sang chuỗi nhị phân
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    // Hàm mã hóa
    public void encryption() {
        // Khởi tạo mảng trạng thái
        int size = (int) Math.pow(2, 3);
        S = new int[size];
        for (int i = 0; i < size; i++) {
            S[i] = i;
        }

        key_list = convertToDecimal(key);
        pt = convertToDecimal(plain_text);

        // Làm cho key_list có độ dài bằng mảng trạng thái
        adjustKeyList();

        // Thực hiện thuật toán KSA
        KSA();

        // Thực hiện thuật toán PGRA
        PGRA();

        // Thực hiện phép XOR giữa luồng khóa và văn bản gốc
        cipherText = XOR(); // Lưu kết quả mã hóa vào biến cipherText
    }

    // Hàm giải mã dữ liệu
    public void decryption() {
        // Khởi tạo mảng trạng thái
        int size = (int) Math.pow(2, 3);
        S = new int[size];
        for (int i = 0; i < size; i++) {
            S[i] = i;
        }

        key_list = convertToDecimal(key);
        pt = convertToDecimal(plain_text);

        // Làm cho key_list có độ dài bằng mảng trạng thái
        adjustKeyList();

        // Thực hiện thuật toán KSA
        KSA();

        // Thực hiện thuật toán PGRA
        PGRA();

        // Thực hiện phép XOR giữa luồng khóa và văn bản gốc
        cipherText = XOR(); // Lưu kết quả mã hóa vào biến cipherText
    }

    // Điều chỉnh danh sách khóa
    public void adjustKeyList() {
        int diff = S.length - key_list.length;
        if (diff > 0) {
            int[] new_key_list = new int[S.length];
            System.arraycopy(key_list, 0, new_key_list, 0, key_list.length);
            for (int i = 0; i < diff; i++) {
                new_key_list[key_list.length + i] = key_list[i % key_list.length];
            }
            key_list = new_key_list;
        }
    }

    // Thuật toán KSA
    public void KSA() {
        int j = 0;
        int N = S.length;

        // Lặp qua khoảng [0, N]
        for (int i = 0; i < N; i++) {
            j = (j + S[i] + key_list[i]) % N;

            // Cập nhật S[i] và S[j]
            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
    }

    // Thuật toán PGRA
    public void PGRA() {
        int N = S.length;
        int i = 0, j = 0;

        // Khởi tạo mảng luồng khóa
        key_stream = new int[pt.length];

        // Lặp qua [0, độ dài của pt]
        for (int k = 0; k < pt.length; k++) {
            i = (i + 1) % N;
            j = (j + S[i]) % N;

            // Cập nhật S[i] và S[j]
            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            int t = (S[i] + S[j]) % N;
            key_stream[k] = S[t];  // Lưu trực tiếp vào key_stream
        }
    }

    // Thực hiện phép XOR giữa luồng khóa và văn bản gốc
    public String XOR() {
        int[] cipherTextArray = new int[pt.length]; // Khởi tạo mảng cipherTextArray
        String result = ""; // Chuỗi lưu kết quả mã hóa

        for (int i = 0; i < pt.length; i++) {
            cipherTextArray[i] = key_stream[i] ^ pt[i]; // Thực hiện phép XOR
        }

        // Chuyển đổi văn bản mã hóa thành chuỗi nhị phân
        for (int i = 0; i < cipherTextArray.length; i++) {
            // Chuyển đổi thành chuỗi nhị phân và đảm bảo độ dài là 3
            result += String.format("%03d", Integer.parseInt(Integer.toBinaryString(cipherTextArray[i])));
        }
        return result; // Trả về kết quả mã hóa để lưu vào cipherText
    }

    // Chuyển đổi chuỗi nhị phân thành số nguyên
    public static int[] convertToDecimal(String input) {
        int length = input.length();
        int[] decimalList = new int[(length + 3 - 1) / 3]; // Tính toán kích thước

        for (int i = 0; i < length; i += 3) {
            String segment = input.substring(i, Math.min(length, i + 3));
            decimalList[i / 3] = Integer.parseInt(segment, 2); // Chuyển đổi từng đoạn thành số nguyên
        }

        return decimalList;
    }

    public void resetCipherText() {
        this.setCipherText("");
    }
}
