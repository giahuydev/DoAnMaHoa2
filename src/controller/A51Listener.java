package controller;

import java.awt.event.*;

import view.A51View;

public class A51Listener implements ActionListener {
    private A51View tinyA51View;

    public A51Listener(A51View tinyA51View) {
        this.tinyA51View = tinyA51View;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Random")) {
            tinyA51View.random();
        } else if (src.equals("Encryption")) {
            tinyA51View.encryption();
        } else if (src.equals("Decryption")) {
            tinyA51View.decryption();
        }
    }

}
