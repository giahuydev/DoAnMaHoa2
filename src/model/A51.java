package model;

import java.util.Random;

public class A51 {
    private String key = null;
    static final int REG_X_LENGTH = 19;
    static final int REG_Y_LENGTH = 22;
    static final int REG_Z_LENGTH = 23;
    int[] regX = new int[REG_X_LENGTH];
    int[] regY = new int[REG_Y_LENGTH];
    int[] regZ = new int[REG_Z_LENGTH];

    public A51() {
        // ?
    }

    public void loadRegisters(String key) {
        for (int i = 0; i < REG_X_LENGTH; i++)
            regX[i] = Integer.parseInt(key.substring(i, i + 1));
        for (int i = 0; i < REG_Y_LENGTH; i++)
            regY[i] = Integer.parseInt(key.substring(REG_X_LENGTH + i, REG_X_LENGTH + i + 1));
        for (int i = 0; i < REG_Z_LENGTH; i++)
            regZ[i] = Integer.parseInt(key.substring(REG_X_LENGTH + REG_Y_LENGTH + i, REG_X_LENGTH +
                    REG_Y_LENGTH + i + 1));
    }

    public boolean setKey(String key) {
        if (key.length() == 64 && key.matches("[01]+")) {
            this.key = key;
            this.loadRegisters(key);
            return true;
        }
        return false;
    }

    public String getKey() {
        return this.key;
    }

    public String encrypt(String plaintext) {
        StringBuilder s = new StringBuilder();
        int[] binary = this.toBinary(plaintext);
        int[] keystream = getKeystream(binary.length);
        for (int i = 0; i < binary.length; i++)
            s.append(binary[i] ^ keystream[i]);
        return s.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder s = new StringBuilder();
        int[] binary = new int[ciphertext.length()];
        int[] keystream = getKeystream(ciphertext.length());
        for (int i = 0; i < binary.length; i++) {
            binary[i] = Integer.parseInt(ciphertext.substring(i, i + 1));
            s.append(binary[i] ^ keystream[i]);
        }
        return this.toStr(s.toString());
    }

    public int[] getKeystream(int length) {
        int[] regX = this.regX.clone();
        int[] regY = this.regY.clone();
        int[] regZ = this.regZ.clone();
        int[] keystream = new int[length];
        for (int i = 0; i < length; i++) {
            int maj = this.getMajority(regX[8], regY[10], regZ[10]);
            if (regX[8] == maj) {
                int newStart = regX[13] ^ regX[16] ^ regX[17] ^ regX[18];
                System.arraycopy(regX, 0, regX, 1, REG_X_LENGTH - 1);
                regX[0] = newStart;
            }
            if (regY[10] == maj) {
                int newStart = regY[20] ^ regY[21];
                System.arraycopy(regY, 0, regY, 1, REG_Y_LENGTH - 1);
                regY[0] = newStart;
            }
            if (regZ[10] == maj) {
                int newStart = regZ[7] ^ regZ[20] ^ regZ[21] ^ regZ[22];
                System.arraycopy(regZ, 0, regZ, 1, REG_Z_LENGTH - 1);
                regZ[0] = newStart;
            }
            keystream[i] = regX[18] ^ regY[21] ^ regZ[22];
        }
        return keystream;
    }

    private int getMajority(int x, int y, int z) {
        return x + y + z > 1 ? 1 : 0;
    }

    public int[] toBinary(String text) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String temp = Integer.toBinaryString(text.charAt(i));
            while (temp.length() < 8)
                temp = "0" + temp;
            s.append(temp);
        }
        int[] binary = new int[s.length()];
        for (int i = 0; i < binary.length; i++)
            binary[i] = Integer.parseInt(s.substring(i, i + 1));
        return binary;
    }

    public String toStr(String binary) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= binary.length() - 8; i += 8)
            s.append((char) Integer.parseInt(binary.substring(i, i + 8), 2));
        return s.toString();
    }

    public String random() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder(64);
        for (int i = 0; i < 64; i++) {
            keyBuilder.append(random.nextInt(2)); // Thêm 0 hoặc 1 ngẫu nhiên
        }
        return keyBuilder.toString();
    }

}
