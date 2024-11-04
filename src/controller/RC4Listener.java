package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.RC4View;

public class RC4Listener implements ActionListener {
    private RC4View rc4View;

    public RC4Listener(RC4View rc4View) {
        this.rc4View = rc4View;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();// lấy chuỗi tên button
        if (src.equals("Encryption")) { // nếu là nút Decryption
            System.out.println("encryption_RC4");
        } else if (src.equals("Decryption")) {// nếu là nút Decryption
            System.out.println("decryption_RC4");
        }
    }

}
