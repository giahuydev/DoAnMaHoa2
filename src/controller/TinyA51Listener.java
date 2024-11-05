package controller;

import java.awt.event.*;

import view.TinyA51View;

public class TinyA51Listener implements ActionListener {
    private TinyA51View tinyA51View;

    public TinyA51Listener(TinyA51View tinyA51View) {
        this.tinyA51View = tinyA51View;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Random")) {
            tinyA51View.random();
        } else if (src.equals("Encryption")) {
            System.out.println("Encryption_TinyA51");
            tinyA51View.encryption();
        } else if (src.equals("Decryption")) {
            System.out.println("Encryption_TinyA51");
            tinyA51View.decryption();
        }
    }

}
